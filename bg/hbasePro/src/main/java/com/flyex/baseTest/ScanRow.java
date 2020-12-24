package com.flyex.baseTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class ScanRow {
    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        Connection conn = ConnectionFactory.createConnection(conf);

        Scan scan = new Scan(Bytes.toBytes("001"),Bytes.toBytes("003"));
        Table table = conn.getTable(TableName.valueOf("test:stu"));
        ResultScanner scanner = table.getScanner(scan);
        for (Result res : scanner){
            Cell[] cells = res.rawCells();
            System.out.print("rk"+ Bytes.toString(res.getRow())+ ",value:");
            for (Cell cell : cells){
                System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

        scanner.close();
        table.close();
    }
}
