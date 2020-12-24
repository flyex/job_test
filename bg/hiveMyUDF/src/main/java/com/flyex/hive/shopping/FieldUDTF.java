package com.flyex.hive.shopping;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FieldUDTF extends GenericUDTF {

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {

        ArrayList<String> fieldsName = new ArrayList<>();
        ArrayList<ObjectInspector> fieldsOI = new ArrayList<>();

        fieldsName.add("event_name");
        fieldsOI.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldsName.add("event_json");
        fieldsOI.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldsName,fieldsOI);
    }

    @Override
    public void process(Object[] objects) throws HiveException {

        String input = objects[0].toString();

        if (StringUtils.isBlank(input)){
            return;
        }else {
            try {

                JSONArray jsonArray = new JSONArray(input);
                if (jsonArray == null){
                    return;
                }

                for (int i = 0; i < jsonArray.length(); i++) {
                    String[] result = new String[2];
                    try {
                       result[0] =  jsonArray.getJSONObject(i).getString("en");
                       result[1] = jsonArray.getString(i);
                    }catch (com.alibaba.fastjson.JSONException e){
                        e.printStackTrace();
                        continue;
                    }
                    forward(result);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public void close() throws HiveException {

    }
}
