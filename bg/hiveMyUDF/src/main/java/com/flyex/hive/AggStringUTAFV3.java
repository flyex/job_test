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

public class AggStringUTAFV3 extends AbstractGenericUDAFResolver {

    @Override
    public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters) throws SemanticException {
        if (parameters.length != 2) {
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


        // 确定各个阶段输入输出参数的数据格式ObjectInspectors
        @Override
        public ObjectInspector init(Mode m, ObjectInspector[] parameters) throws HiveException {
            assert (parameters.length == 2);
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
            StringBuilder sbMan;
            StringBuilder sbWoman;
            String sex;

            public StringSum(){

                sbMan = new StringBuilder();
                sbWoman = new StringBuilder();
            }


            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public void sumString(String s){
                if (sex.equals("man")){
                    sbMan.append(s);
                }else {
                    sbWoman.append(s);
                }
            }
        }


        // 保存数据聚集结果的类
        @Override
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            StringSum res = new StringSum();
            return res;
        }

        @Override
        public void reset(AggregationBuffer aggregationBuffer) throws HiveException {
            StringSum myAgg = new StringSum();
        }

        //private boolean warned = false;


        // map阶段，迭代处理输入sql传过来的列数据
        @Override
        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {
            assert (parameters.length == 2);

            if (parameters[0] != null){
                StringSum myagg = (StringSum) agg;
                Object p1 = ((PrimitiveObjectInspector) inputOI).getPrimitiveJavaObject(parameters[0]);
                if (parameters[1].equals("man")){
                    myagg.setSex("man");
                    myagg.sumString(String.valueOf(p1+ "*itr阶段*"));
                }else {
                    myagg.setSex("woman");
                    myagg.sumString(String.valueOf(p1+ "*itr阶段*"));
                }
            }

            /**
             if (parameters[0] != null){
             StringSum myagg = (StringSum) agg;
             Object p1 = ((PrimitiveObjectInspector) inputOI).getPrimitiveJavaObject(parameters[0]);
             myagg.sumString(String.valueOf(p1+ "*itr阶段*"));
             }
             */
        }

        // map与combiner结束返回结果，得到部分数据聚集结果
        @Override
        public Object terminatePartial(AggregationBuffer agg) throws HiveException {

            StringBuilder manTotal = new StringBuilder();
            StringBuilder womanTotal = new StringBuilder();
            StringSum myagg = (StringSum) agg;

            if (myagg.getSex().equals("man")){
                manTotal.append(myagg.sbMan+ "*terminatePartial*");
                return manTotal;
            }else {
                womanTotal.append(myagg.sbWoman+ "*terminatePartial*");
                return womanTotal;
            }
        }



        // combiner合并map返回的结果，还有reducer合并mapper或combiner返回的结果。
        @Override
        public void merge(AggregationBuffer agg, Object partial) throws HiveException {
            if (partial != null){
                StringSum myagg1 = (StringSum) agg;

                String partialSum = (String) inputOI.getPrimitiveJavaObject(partial);

//                StringSum myagg2 = new StringSum();
//
//                myagg2.sumString(partialSum);
//                System.out.println("merge的输出");
//                myagg1.sumString(myagg2.sb.toString()+ "*merge*");
                myagg1.sumString(partialSum+ "*merge*");
            }
        }


        // reducer阶段，输出最终结果
        @Override
        public Object terminate(AggregationBuffer agg) throws HiveException {
//            StringBuilder total2 = new StringBuilder();

            StringSum myagg = (StringSum) agg;

//            total2 = myagg.sb.append("*terminate*");

            if (myagg.getSex().equals("man")){
                return myagg.sbMan;
            }else {
                return myagg.sbWoman;
            }
        }
    }
}
