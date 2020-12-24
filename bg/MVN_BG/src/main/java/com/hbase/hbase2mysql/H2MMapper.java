package com.hbase.hbase2mysql;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.HashMap;

public class H2MMapper extends TableMapper<Text,Text> {

    HashMap map = new HashMap<String,String>();
    Text text1 = new Text();
    Text text2 = new Text();

    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {

        map.clear();

        String rowKey = Bytes.toString(key.get());

        Cell[] cells = value.rawCells();
        for (Cell cell : cells){
            map.put(Bytes.toString(CellUtil.cloneFamily(cell)),Bytes.toString(CellUtil.cloneValue(cell)));
        }

        text1.set(rowKey);
        text2.set(map.toString());
        context.write(text1,text2);

    }
}
