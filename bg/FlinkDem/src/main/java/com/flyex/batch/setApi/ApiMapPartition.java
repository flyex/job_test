package com.flyex.batch.setApi;

import org.apache.flink.api.common.functions.MapPartitionFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapPartitionOperator;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Iterator;

public class ApiMapPartition {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        ArrayList<String> data = new ArrayList<>();
        data.add("hello you");
        data.add("good me");

        DataSource<String> text = env.fromCollection(data).setParallelism(3);

        DataSet<String> mapPartition = text.mapPartition(new MapPartitionFunction<String, String>() {
            @Override
            public void mapPartition(Iterable<String> values, Collector<String> out) throws Exception {
                System.out.println("线程id:" + Thread.currentThread().getId());
                Iterator<String> itr = values.iterator();
                while (itr.hasNext()) {
                    String next = itr.next();
                    String[] words = next.split(" ");
                    for (String word : words) {
                        out.collect(word);
                    }
                }
            }
        });

        mapPartition.print();

    }
}
