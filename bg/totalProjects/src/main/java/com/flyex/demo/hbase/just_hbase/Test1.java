package com.flyex.demo.hbase.just_hbase;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

/*
测试使用java api操作hbase
 */
public class Test1 {
    Connection conn = null;
    @Before
    public void getConn() throws Exception{

        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","hdp-02:2181,hdp-03:2181,hdp-04;2181");

        conn = ConnectionFactory.createConnection(conf);
    }

    //建表
    @Test
    public void c_table() throws IOException {

        Admin admin = conn.getAdmin();
        HTableDescriptor student = new HTableDescriptor(TableName.valueOf("student"));

        HColumnDescriptor base_info = new HColumnDescriptor("base_info");
        base_info.setMaxVersions(3);
        HColumnDescriptor extra_info = new HColumnDescriptor("extra_info");

        student.addFamily(base_info);
        student.addFamily(extra_info);

        admin.createTable(student);

        admin.close();
        conn.close();

    }

    //给原有表添加一个列族
    @Test
    public void add_new_t() throws IOException {

        Admin admin = conn.getAdmin();

        HTableDescriptor student = admin.getTableDescriptor(TableName.valueOf("student"));

        HColumnDescriptor other_info = new HColumnDescriptor("other_info");
        student.addFamily(other_info);

        admin.modifyTable(TableName.valueOf("student"),student);

        admin.close();
        conn.close();

    }

    //测试UUid
    @Test
    public void put_test(){

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().substring(0,8));
        System.out.println("name".hashCode());
    }
}
