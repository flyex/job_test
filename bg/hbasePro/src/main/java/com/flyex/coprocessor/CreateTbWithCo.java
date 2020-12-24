package com.flyex.coprocessor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class CreateTbWithCo {

    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();

        Connection conn = ConnectionFactory.createConnection(conf);

        Admin admin = conn.getAdmin();

        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("test:stuWithCo"));
        hTableDescriptor.addFamily(new HColumnDescriptor("cf1"));
        hTableDescriptor.addFamily(new HColumnDescriptor("cf2"));

        hTableDescriptor.addCoprocessor("com.flyex.coprocessor.CoprocessorObserver");

        String[] keys = {"00|", "01|", "02|", "03|", "04|"};
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

        admin.createTable(hTableDescriptor,splitKeys);

        admin.close();
        conn.close();

    }
}
