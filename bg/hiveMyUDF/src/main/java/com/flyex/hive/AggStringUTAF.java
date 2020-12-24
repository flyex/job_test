package com.flyex.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;

public class AggStringUTAF extends AbstractGenericUDAFResolver {

    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters) throws SemanticException {
        if (parameters.length != 1) {
            throw new UDFArgumentTypeException(parameters.length - 1,
                    "Exactly one argument is expected.");
        }

        ObjectInspector oi = TypeInfoUtils.getStandardJavaObjectInspectorFromTypeInfo(parameters[0]);

        if (oi.getCategory() != ObjectInspector.Category.PRIMITIVE){
            throw new UDFArgumentTypeException(0,
                    "Argument must be PRIMITIVE, but "
                            + oi.getCategory().name()
                            + " was passed.");
        }

        PrimitiveObjectInspector inputOI = (PrimitiveObjectInspector) oi;

        if (inputOI.getPrimitiveCategory() != PrimitiveObjectInspector.PrimitiveCategory.STRING){
            throw new UDFArgumentTypeException(0,
                    "Argument must be String, but "
                            + inputOI.getPrimitiveCategory().name()
                            + " was passed.");
        }

        return new MergeWordEvaluator();
    }

    public static class MergeWordEvaluator extends GenericUDAFEvaluator {

        PrimitiveObjectInspector inputOI;
        ObjectInspector outputOI;
        //PrimitiveObjectInspector inputOII;

        //StringBuilder total;

        @Override
        public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
            assert (parameters.length == 1);
            super.init(m,parameters);

            inputOI = (PrimitiveObjectInspector)parameters[0];

            outputOI = ObjectInspectorFactory.getReflectionObjectInspector(String.class,
                                        ObjectInspectorFactory.ObjectInspectorOptions.JAVA);

            return outputOI;
        }

        /**
         * 保存缓存string的类
         */
        static class StringSum implements AggregationBuffer{
            StringBuilder sb = new StringBuilder();

            public StringBuilder getSb() {
                return sb;
            }

            public void setSb(StringBuilder sb) {
                this.sb = sb;
            }

            void sumString(String s){
                sb.append(s);
            }
        }

        @Override
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            StringSum res = new StringSum();
            return res;
        }

        @Override
        public void reset(AggregationBuffer agg) throws HiveException {
            StringSum myAgg = new StringSum();
        }

        @Override
        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
            assert (parameters.length == 1);
            if (parameters[0] != null){
                StringSum myagg = (StringSum) agg;
                Object p1 = ((PrimitiveObjectInspector) inputOI).getPrimitiveJavaObject(parameters[0]);
                myagg.sumString(String.valueOf(p1));
            }
        }

        @Override
        public Object terminatePartial(AggregationBuffer agg) throws HiveException {
            StringBuilder total = new StringBuilder();
            StringSum myagg = (StringSum) agg;
            total.append(myagg.sb);
            return total;
        }

        @Override
        public void merge(AggregationBuffer agg, Object partial) throws HiveException {
            if (partial != null){
                StringSum myagg1 = (StringSum) agg;

                String partialSum = (String) inputOI.getPrimitiveJavaObject(partial);

//                StringSum myagg2 = new StringSum();
//                myagg2.sumString(partialSum);

                myagg1.sumString(partialSum);
            }
        }

        @Override
        public Object terminate(AggregationBuffer agg) throws HiveException {
            StringSum myagg = (StringSum) agg;

            return myagg.sb;
        }

    }
}
