package com.hbase.hbase2mysql;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class H2MReducer extends Reducer<Text,Text,Text,Text> {

    Text text1 = new Text();
    Text text2 = new Text();
    StringBuilder sb = new StringBuilder();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        sb.delete(0,sb.length());

        Iterator<Text> iterator = values.iterator();
        while (iterator.hasNext()){
            String sbb = iterator.next().toString();
            sb.append(sbb);
        }

        text1.set(key);
        text2.set(sb.toString());

        context.write(text1,text2);
    }
}
