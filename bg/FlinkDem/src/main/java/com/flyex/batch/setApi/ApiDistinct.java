package com.flyex.batch.setApi;

import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.DistinctOperator;
import org.apache.flink.api.java.tuple.Tuple2;

import java.util.ArrayList;

public class ApiDistinct {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        ArrayList<Tuple2<Integer,String>> tuplesList = new ArrayList<>();
        tuplesList.add(new Tuple2<Integer,String>(1,"zx"));
        tuplesList.add(new Tuple2<Integer,String>(2,"zh"));
        tuplesList.add(new Tuple2<Integer,String>(3,"zx"));

        DataSource<Tuple2<Integer,String>> data = env.fromCollection(tuplesList);

        //将数据按第一列分组后，再在组内进行升序排序，再取得每组内前两条数据
        //sortGroup只能在分组后用
        data.groupBy(1).sortGroup(2, Order.ASCENDING).first(2).print();

        //不分组，全局排序获取前三个元素，针对第一个元素升序，第二个原叙倒序
        data.sortPartition(0,Order.ASCENDING).sortPartition(1,Order.DESCENDING).first(3).print();

    }
}
