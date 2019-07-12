package com.flyex.myhive;

import org.apache.hadoop.hive.ql.exec.UDF;

public class Myfunction extends UDF {

    public String evaluate(String field,int index){

        String fields[] = field.split(":");

        return fields[index-1];
    }
}
