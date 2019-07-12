package com.flyex.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class WordCountDemo {
    public static void main(String[] args) throws Exception{
        FileSystem fs = FileSystem.get(new URI("hdfs://hdp-01:9000"), new Configuration(), "root");
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/intput"),false);

        HashMap<Object,Object> countTotal = new HashMap<Object, Object>();
        while (iterator.hasNext()){
            LocatedFileStatus status = iterator.next();
            FSDataInputStream inputStream = fs.open(status.getPath());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;
            while ((line=br.readLine())!=null){
                String[] ls = line.split(" ");
                for (String s:ls){
                    Object o = countTotal.get(s);
                    if (o==null){
                        countTotal.put(s,1);
                    }else {
                        countTotal.put(s,(int)o+1);
                    }
                }
            }
            System.out.println("统计的结果是：");
            for (Map.Entry<Object,Object> entry:countTotal.entrySet()){
                System.out.println(entry.getKey()+"\t"+entry.getValue());
            }

            br.close();
            inputStream.close();
        }

        FSDataOutputStream fsDataOutputStream = fs.create(new Path("/output","result.dat"));
        for (Map.Entry<Object,Object> entry:countTotal.entrySet()){
            fsDataOutputStream.write((entry.getKey().toString()+"\t"+entry.getValue().toString()+"\n").getBytes());
        }
        fs.close();
        fsDataOutputStream.close();
    }
}
