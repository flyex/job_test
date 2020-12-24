package com.flyex.streaming.streamApi;

import com.flyex.streaming.custormSource.MyNoParalleSource;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;

public class ApiSplit {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Long> source = env.addSource(new MyNoParalleSource()).setParallelism(1);

        SplitStream<Long> splited = source.split(new OutputSelector<Long>() {
            @Override
            public Iterable<String> select(Long value) {
                ArrayList<String> out = new ArrayList<>();
                if (value % 2 == 0) {
                    out.add("even");
                } else {
                    out.add("odd");
                }
                return out;
            }
        });

        //选择一个流
        DataStream<Long> even = splited.select("even");
        DataStream<Long> odd = splited.select("odd");
        DataStream<Long> all = splited.select("even", "odd");

        even.print().setParallelism(1);

        env.execute("as");


    }
}
