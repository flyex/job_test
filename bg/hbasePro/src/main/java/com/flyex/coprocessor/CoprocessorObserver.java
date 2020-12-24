package com.flyex.coprocessor;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Random;

public class CoprocessorObserver extends BaseRegionObserver {

    //put为上一条put的内容
    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
        super.postPut(e, put, edit, durability);

        String currentTableName = e.getEnvironment().getRegionInfo().getTable().getNameAsString();

        if (!"test:stuWithCo".equals(currentTableName)) return;

        String rowkey = Bytes.toString(put.getRow());

        //防止循环插入
        if (rowkey.length()>5) return;

        Random random = new Random();
        StringBuilder newkey = new StringBuilder();
        newkey.append(rowkey).append("+co"+random.nextInt(5));

        Put coPut = new Put(Bytes.toBytes(newkey.toString()));
        coPut.addColumn(Bytes.toBytes("cf1"),Bytes.toBytes("status"),Bytes.toBytes("协处理器添加成功"));

        HTableInterface table = e.getEnvironment().getTable(TableName.valueOf("test:stuWithCo"));
        table.put(coPut);
        table.close();

    }
}
