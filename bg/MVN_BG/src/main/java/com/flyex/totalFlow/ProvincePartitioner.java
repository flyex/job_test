package com.flyex.totalFlow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

public class ProvincePartitioner extends Partitioner<Text,FlowBean> {
    static HashMap<String,Integer> codeMap =new HashMap<String, Integer>();
    static {
        codeMap.put("135",0);
        codeMap.put("136",1);
        codeMap.put("137",2);
        codeMap.put("138",3);
        codeMap.put("139",4);
    }
    @Override
    public int getPartition(Text key,FlowBean value,int numPartitions) {
        Integer code = codeMap.get(key.toString().substring(0,3));
        return code==null?5:code;
    }
}
