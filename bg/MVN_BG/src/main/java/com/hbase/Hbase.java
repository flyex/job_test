package com.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.annotation.ExceptionProxy;

import java.util.ArrayList;
import java.util.Iterator;

public class Hbase {

    Connection conn = null;

    @Before
    public void getConn() throws Exception{
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hdp-02:2181,hdp-03:2181,hdp-04:2181");

        conn = ConnectionFactory.createConnection(conf);
    }

    @Test
    public void testCreateTable() throws Exception{

        Admin admin = conn.getAdmin();

        //创建表定义描述对象
        HTableDescriptor fruit_info = new HTableDescriptor(TableName.valueOf("fruit_info"));
        //创建列族定义描述对象
        HColumnDescriptor base_info = new HColumnDescriptor("base_info");
        base_info.setMaxVersions(3);    //设置该列族存储数据的最大版本数,默认1

        HColumnDescriptor extra_info = new HColumnDescriptor("extra_info");

        //将列祖定义信息放入表对象里
        fruit_info.addFamily(base_info);
        fruit_info.addFamily(extra_info);

        admin.createTable(fruit_info);

        admin.close();
        conn.close();

    }

    @Test
    public void testDeleteTable() throws Exception{

        Admin admin = conn.getAdmin();

        admin.disableTable(TableName.valueOf("fruit_info"));
        admin.deleteTable(TableName.valueOf("fruit_info"));

        admin.close();
        conn.close();
    }

    @Test
    public void testAlterColumn() throws Exception{
        Admin admin = conn.getAdmin();

        HTableDescriptor fruit_info = admin.getTableDescriptor(TableName.valueOf("fruit_info"));

        HColumnDescriptor other_info = new HColumnDescriptor("other_info");
        other_info.setBloomFilterType(BloomType.ROWCOL);

        fruit_info.addFamily(other_info);

        admin.modifyTable(TableName.valueOf("fruit_info"),fruit_info);

        admin.close();
        conn.close();
    }

    //DML之插入 put 改：覆盖
    @Test
    public void putTest() throws Exception{

        Table fruit_info = conn.getTable(TableName.valueOf("fruit_info"));

        Put put = new Put(Bytes.toBytes("001"));
        put.addColumn(Bytes.toBytes("base_info"),Bytes.toBytes("fruitName"),Bytes.toBytes("apple"));
        put.addColumn(Bytes.toBytes("extra_info"),Bytes.toBytes("grown"),Bytes.toBytes("four-months"));
        put.addColumn(Bytes.toBytes("other_info"),Bytes.toBytes("advantage"),Bytes.toBytes("can lose weight"));

        Put put2 = new Put(Bytes.toBytes("002"));
        put2.addColumn(Bytes.toBytes("base_info"),Bytes.toBytes("fruitName"),Bytes.toBytes("banana"));
        put2.addColumn(Bytes.toBytes("other_info"),Bytes.toBytes("advantage"),Bytes.toBytes("顺肠道"));

        ArrayList<Put> puts = new ArrayList<Put>();
        puts.add(put);
        puts.add(put2);

        fruit_info.put(puts);

        fruit_info.close();
        conn.close();
    }

    //get
    @Test
    public void getTest() throws Exception{

        Table fruit_info = conn.getTable(TableName.valueOf("fruit_info"));

        Get get = new Get("002".getBytes());

        Result result = fruit_info.get(get);

        byte[] value = result.getValue("other_info".getBytes(), "advantage".getBytes());

        System.out.println(new String(value));

        CellScanner cellScanner = result.cellScanner();
        while (cellScanner.advance()){

            Cell cell = cellScanner.current();

            byte[] rowArray = cell.getRowArray();
            byte[] familyArray = cell.getFamilyArray();
            byte[] qualifierArray = cell.getQualifierArray();
            byte[] valueArray = cell.getValueArray();

            System.out.println("列名: "+new String(qualifierArray,cell.getQualifierOffset(),cell.getQualifierLength()));
            System.out.println("value: "+new String(valueArray,cell.getValueOffset(),cell.getValueLength()));
        }

        fruit_info.close();
        conn.close();
    }

    //scan
    @Test
    public void scanTest() throws Exception{

        Table fruit_info = conn.getTable(TableName.valueOf("fruit_info"));

        Scan scan = new Scan("001".getBytes(), "010\001".getBytes());

        ResultScanner scanner = fruit_info.getScanner(scan);

        Iterator<Result> iterator = scanner.iterator();

        while (iterator.hasNext()){

            Result next = iterator.next();
            CellScanner cellScanner = next.cellScanner();

            while (cellScanner.advance()){

                Cell cell = cellScanner.current();

                byte[] rowArray = cell.getRowArray();  //本kv所属的行键的字节数组
                byte[] familyArray = cell.getFamilyArray();  //列族名的字节数组
                byte[] qualifierArray = cell.getQualifierArray();  //列名的字节数据
                byte[] valueArray = cell.getValueArray(); // value的字节数组

                System.out.println("行键: "+new String(rowArray,cell.getRowOffset(),cell.getRowLength()));
                System.out.println("列族名: "+new String(familyArray,cell.getFamilyOffset(),cell.getFamilyLength()));
                System.out.println("列名: "+new String(qualifierArray,cell.getQualifierOffset(),cell.getQualifierLength()));
                System.out.println("value: "+new String(valueArray,cell.getValueOffset(),cell.getValueLength()));
            }

            System.out.println("---------------------------------------------------");
        }

        fruit_info.close();
        conn.close();

    }

    @Test
    public void test(){

        String a  = "000";
        String b = "000\0";

        System.out.println(a+"|"+b);

        byte[] aBytes = a.getBytes();
        byte[] bBytes = b.getBytes();

        System.out.println(aBytes+"|"+bBytes);
    }

}
