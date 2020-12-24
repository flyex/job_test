package com.flyex.baseTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class Test {

    public static void main(String[] args) {

        int[][] ints = new int[3][];
        int[] ints1 = new int[2];
        ints1[0] = 1;
        ints1[1] = 2;

        ints[0] = ints1;
        ints[0] = ints1;
        ints[1] = ints1;
        ints[1] = ints1;
        ints[2] = ints1;
        ints[2] = ints1;


        for (int i= 0;i< ints.length;i++){
            for (int j = 0; j<ints[i].length; j++){
                //System.out.print(ints[i][j]+ "\t");
            }
            //System.out.println();
        }

        Configuration conf = HBaseConfiguration.create();
        Connection conn = null;
        try {
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SingleColumnValueFilter valueFilter = new SingleColumnValueFilter(Bytes.toBytes("cf1"), Bytes.toBytes("name"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes("xiaoli"));
        valueFilter.setLatestVersionOnly(true);
        valueFilter.setFilterIfMissing(true);

        Scan scan = new Scan();
        scan.setFilter(valueFilter);

        ResultScanner scanner = null;
        try {
            Table table = conn.getTable(TableName.valueOf("test:stuWithCo"));
            scanner = table.getScanner(scan);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Result res : scanner){
            for (Cell cell : res.rawCells()){
                System.out.println("rowkey:"+ Bytes.toString(CellUtil.cloneRow(cell))
                        + ",columnFamily:"+ Bytes.toString(CellUtil.cloneFamily(cell))
                        + ",qualifier:"+ Bytes.toString(CellUtil.cloneQualifier(cell))
                        + ",value"+ Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

    }
}
