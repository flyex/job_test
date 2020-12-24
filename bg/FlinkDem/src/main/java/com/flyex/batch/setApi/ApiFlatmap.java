package com.flyex.batch.setApi;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.FlatMapIterator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Iterator;

public class ApiFlatmap {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        ArrayList<String> lines = new ArrayList<>();
        lines.add("dog");
        lines.add("pig");
        lines.add("cat");

        DataSource<String> line = env.fromCollection(lines);

        //line.print();

        FlatMapOperator<String, String> flatMapped = line.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                String[] words = value.split(",");
                for (String word :
                        words) {
                    out.collect(word);
                }
            }
        });

        flatMapped.print();

    }
}
