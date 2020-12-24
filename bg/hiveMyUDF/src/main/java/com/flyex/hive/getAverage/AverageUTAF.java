package com.flyex.hive.getAverage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.objectinspector.*;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.util.StringUtils;

import java.util.ArrayList;

@Description(name = "myavg", value = "_FUNC_(x) - Returns the mean of a set of numbers")
public class AverageUTAF extends AbstractGenericUDAFResolver {

    static final Log LOG = LogFactory.getLog(AverageUTAF.class.getName());

    //读入参数类型校验，满足条件时返回聚合函数数据处理对象
    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters) throws SemanticException {
        if (parameters.length != 1) {
            throw new UDFArgumentTypeException(parameters.length - 1,
                    "Exactly one argument is expected.");
        }

        if (parameters[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
            throw new UDFArgumentTypeException(0,
                    "Only primitive type arguments are accepted but "
                            + parameters[0].getTypeName() + " is passed.");
        }
        switch (((PrimitiveTypeInfo) parameters[0]).getPrimitiveCategory()) {
            case BYTE:
            case SHORT:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STRING:
            case TIMESTAMP:
                return new GenericUDAFAverageEvaluator();
            case BOOLEAN:
            default:
                throw new UDFArgumentTypeException(0,
                        "Only numeric or string type arguments are accepted but "
                                + parameters[0].getTypeName() + " is passed.");
        }
    }

    public static class GenericUDAFAverageEvaluator extends GenericUDAFEvaluator {

        //TODO 全局定义输入输出数据类型的OI，解析输入输出数据
        //input for PARTIAL1 and COMPLETE (1,3,4,5,numbs,..)
        PrimitiveObjectInspector inputOI;

        //input for PARTIAL2 and FINAL
        StructObjectInspector soi;
        StructField countField;
        StructField sumField;
        LongObjectInspector countFieldOI;
        DoubleObjectInspector sumFieldOI;

        //TODO 定义全局输出类型，用于存储实际数据
        //output for PARTIAL1 and PARTIAL2  (sum,count)
        Object[] partialResult;

        //output for FINAL and COMPLETE (sum/count)
        DoubleWritable result;

        //for ALL mode,need judge
        @Override
        public ObjectInspector init(Mode mode, ObjectInspector[] parameters) throws HiveException {
            assert (parameters.length == 1);
            super.init(mode, parameters);

            //init input
            if (mode == Mode.PARTIAL1 || mode == Mode.COMPLETE) {
                inputOI = (PrimitiveObjectInspector) parameters[0];  //1、2、3、4...
            } else {
                soi = (StructObjectInspector) parameters[0];
                countField = soi.getStructFieldRef("count");
                sumField = soi.getStructFieldRef("sum");
                //实例化数组每个数据
                countFieldOI = (LongObjectInspector) countField.getFieldObjectInspector();
                sumFieldOI = (DoubleObjectInspector) sumField.getFieldObjectInspector();
            }

            //init output
            if (mode == Mode.PARTIAL1 || mode == Mode.PARTIAL2) {
                //map and combine stage's result are an array [count,sum]
                partialResult = new Object[2];
                partialResult[0] = new LongWritable(0);
                partialResult[1] = new DoubleWritable(0);

                /*
                构造Struct的OI实例，设定聚合结果数据的类型
                需要字段名和字段类型
                 */
                ArrayList<String> fname = new ArrayList<String>();
                fname.add("count");
                fname.add("sum");
                ArrayList<ObjectInspector> foi = new ArrayList<ObjectInspector>();
                //注：此处的两个OI类型 描述的是 partialResult[] 的两个类型，故需一致
                foi.add(PrimitiveObjectInspectorFactory.writableLongObjectInspector);
                foi.add(PrimitiveObjectInspectorFactory.writableDoubleObjectInspector);
                return ObjectInspectorFactory.getStandardStructObjectInspector(fname, foi);
            } else { //reduce and complete stage
                result = new DoubleWritable(0);
                return PrimitiveObjectInspectorFactory.writableDoubleObjectInspector;
            }
        }

        //聚合数据缓存存储结构
        static class AverageAgg implements AggregationBuffer {
            long count;
            double sum;
        }

        @Override
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            AverageAgg result = new AverageAgg();
            reset(result);
            return result;
        }

        @Override
        public void reset(AggregationBuffer agg) throws HiveException {
            AverageAgg myagg = (AverageAgg) agg;
            myagg.count = 0;
            myagg.sum = 0;
        }

        boolean warned = false;

        //map -> PARTIAL1 遍历数据
        @Override
        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
            assert (parameters.length == 1);
            Object p = parameters[0];
            if (p != null) {
                AverageAgg myagg = (AverageAgg) agg;
                try {
                    //通过基本数据类型OI解析p的值
                    double v = PrimitiveObjectInspectorUtils.getDouble(p, inputOI);
                    myagg.count++;
                    myagg.sum += v;
                } catch (NumberFormatException e) {
                    if (!warned) {
                        warned = true;
                        LOG.warn(getClass().getSimpleName() + " "
                                + StringUtils.stringifyException(e));
                        LOG.warn(getClass().getSimpleName()
                                + " ignoring similar exceptions.");
                    }
                }
            }
        }

        //部分结果聚合PARTIAL1(map)、PARTIAL2(combine)
        @Override
        public Object terminatePartial(AggregationBuffer agg) throws HiveException {
            AverageAgg myagg = (AverageAgg)agg;
            ((LongWritable)partialResult[0]).set(myagg.count);
            ((DoubleWritable)partialResult[1]).set(myagg.sum);
            return partialResult;
        }

        //PARTIAL2 and FINAL合并部分聚合结果
        @Override
        public void merge(AggregationBuffer agg, Object partial) throws HiveException {
            if (partial != null){
                AverageAgg myagg = (AverageAgg)agg;
                //通过StandardStructObjectInspector实例分解出partial数组元素值
                Object partialCount = soi.getStructFieldData(partial, countField);
                Object partialSum = soi.getStructFieldData(partial, sumField);
                //通过基本数据类型OI实例解析object的值
                myagg.count += countFieldOI.get(partialCount);
                myagg.sum += sumFieldOI.get(partialSum);

            }
        }

        //最终聚合结果 FINAL(reduce) and COMPLETE(only map to all)
        @Override
        public Object terminate(AggregationBuffer agg) throws HiveException {
            AverageAgg myagg = (AverageAgg)agg;
            if (myagg.count == 0){
                return null;
            }else {
                result.set(myagg.sum/myagg.count);
                return result;
            }
        }
    }
}
