package com.flyex.baseTest;

import org.apache.commons.math3.analysis.function.Exp;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.protobuf.generated.FilterProtos;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HbaseDML {

    Connection conn = null;

    @Before
    public void getConn() throws IOException {
        //获取hbase等配置文件
        Configuration conf = HBaseConfiguration.create();
        //创建连接
        conn = ConnectionFactory.createConnection(conf);
    }


    @Test
    public void putTest() throws Exception {
        Table table = conn.getTable(TableName.valueOf("test:stu"));

        Put put1 = new Put(Bytes.toBytes("001"));
        put1.addColumn(Bytes.toBytes("baseInfo"), Bytes.toBytes("name"), Bytes.toBytes("john"));

        Put put2 = new Put(Bytes.toBytes("002"));
        put2.addColumn(Bytes.toBytes("baseInfo"), Bytes.toBytes("name"), Bytes.toBytes("flyex"));
        put2.addColumn(Bytes.toBytes("otherInfo"), Bytes.toBytes("habit"), Bytes.toBytes("i like swimming"));

        ArrayList<Put> puts = new ArrayList<>();
        puts.add(put1);
        puts.add(put2);

        //直接put多个值
        table.put(puts);

        table.close();
        conn.close();
    }

    @Test
    public void deleteTest() throws IOException{
        Table table = conn.getTable(TableName.valueOf("test:game"));

        //删除一整行
        Delete delete1 = new Delete(Bytes.toBytes("001"));
        //删除改行一个cell
        Delete delete2 = new Delete(Bytes.toBytes("002"));
        delete2.addColumn(Bytes.toBytes("extraInfo"),Bytes.toBytes("address"));

        ArrayList<Delete> deletes = new ArrayList<>();
        deletes.add(delete1);
        deletes.add(delete2);

        table.delete(deletes);
        table.close();
        conn.close();

    }

    @Test
    public void getTest() throws IOException{
        Table table = conn.getTable(TableName.valueOf("test:stu"));

        //获取单行的值
        Get get = new Get("002".getBytes());
        Result result = table.get(get);
        byte[] value = result.getValue("otherInfo".getBytes(), "habit".getBytes());

        System.out.println(new String(value));
        table.close();
        conn.close();
    }

    @Test
    public void test() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = sdf.parse("2020-05");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);

        System.out.println(calendar.getTime());


    }

    /**
     * 测试filter
     */
    @Test
    public void filterTest() throws IOException{

        System.out.println(2^1);
    }

    @Test
    public void testScan(){
        
    }
}
