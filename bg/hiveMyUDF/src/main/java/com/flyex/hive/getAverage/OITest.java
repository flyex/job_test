package com.flyex.hive.getAverage;

//import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.serde2.objectinspector.*;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;

import java.util.ArrayList;

//@Description(name = "myavg", value = "_FUNC_(x) - Returns the mean of a set of numbers")
public class OITest {

    public static void main(String[] args) {

        ArrayList<ObjectInspector> foi = new ArrayList<>();

        foi.add(PrimitiveObjectInspectorFactory.javaLongObjectInspector);
        foi.add(PrimitiveObjectInspectorFactory.javaDoubleObjectInspector);

        ArrayList<String> fname = new ArrayList<>();
        fname.add("count");
        fname.add("sum");

        StructObjectInspector soi = ObjectInspectorFactory.getStandardStructObjectInspector(fname, foi);

        StructField countField = soi.getStructFieldRef("count");
        StructField sumField = soi.getStructFieldRef("sum");

        LongObjectInspector countFieldOI = (LongObjectInspector) countField.getFieldObjectInspector();
        DoubleObjectInspector sumFieldOI = (DoubleObjectInspector) sumField.getFieldObjectInspector();

        Object[] p = new Object[2];
        p[0] = new Long(1);
        p[1] = new Double(2.0);

        Object pCount = soi.getStructFieldData(p, countField);
        Object pSum = soi.getStructFieldData(p, sumField);

//        long count = countFieldOI.get(pCount);
//        double sum = sumFieldOI.get(pSum);

        long count = PrimitiveObjectInspectorUtils.getLong(pCount, countFieldOI);
        double sum = PrimitiveObjectInspectorUtils.getDouble(pSum, sumFieldOI);

        System.out.println("count:"+count+",sum:"+sum);

    }

}
