package com.flyex.baseTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class PreSplitRegion {

    private Connection conn = null;

    @Before
    public void createConf() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conn = ConnectionFactory.createConnection(conf);
    }

    @Test
    public void createTableWithSplit() throws Exception{
        //初始化
        String[] keys = {"00|", "01|", "02|", "03|", "04|", "05|"};
        byte[][] splitKeys = new byte[keys.length][];

        TreeSet<byte[]> treeSet = new TreeSet<>(Bytes.BYTES_COMPARATOR);

        for (int i = 0;i<keys.length;i++){
            treeSet.add(Bytes.toBytes(keys[i]));
        }

        Iterator<byte[]> iterator = treeSet.iterator();
        int index = 0;

        while (iterator.hasNext()){
            splitKeys[index++] = iterator.next();
        }

        Admin admin = conn.getAdmin();

        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("test:stu2"));
        hTableDescriptor.addFamily(new HColumnDescriptor("cf1"));
        hTableDescriptor.addFamily(new HColumnDescriptor("cf2"));

        admin.createTable(hTableDescriptor,splitKeys);
        admin.close();
        conn.close();

    }
}
