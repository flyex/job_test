package com.flyex.baseTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class HbaseDDL {

    public static void main(String[] args) throws IOException {

        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);

        Admin admin = conn.getAdmin();
//        创建表描述器
//        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("test:stu"));
//        创建列族
//        HColumnDescriptor cf1_BaseInfo = new HColumnDescriptor("baseInfo");
        //设置列族数据最大版本号，默认为1
//        cf1_BaseInfo.setMaxVersions(2);
//        HColumnDescriptor cf2_ExtraInfo = new HColumnDescriptor("extraInfo");
//        cf2_ExtraInfo.setMaxVersions(2);
//
//        hTableDescriptor.addFamily(cf1_BaseInfo);
//        hTableDescriptor.addFamily(cf2_ExtraInfo);
//
//        admin.createTable(hTableDescriptor);


        //从以前的表中获取表描述器
        //增加新列族
        HTableDescriptor tableDescriptor = admin.getTableDescriptor(TableName.valueOf("test:stu"));

        HColumnDescriptor cf3_OtherInfo = new HColumnDescriptor("otherInfo");

        tableDescriptor.addFamily(cf3_OtherInfo);

        admin.modifyTable(TableName.valueOf("test:stu"),tableDescriptor);

        admin.close();
        conn.close();


    }
}
