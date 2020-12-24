package com.flyex.streaming.custormSource;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

public class UseMyParallelSource {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Long> streamSource = env.addSource(new MyParallelSource()).setParallelism(2);

        SingleOutputStreamOperator<Long> mapped = streamSource.map(new MapFunction<Long, Long>() {
            @Override
            public Long map(Long value) throws Exception {
                System.out.println("接收的数据为：" + value);
                return value;
            }
        });

        SingleOutputStreamOperator<Long> result = mapped.timeWindowAll(Time.seconds(2)).sum(0);

        result.print().setParallelism(1);

        env.execute("aha");

    }
}
