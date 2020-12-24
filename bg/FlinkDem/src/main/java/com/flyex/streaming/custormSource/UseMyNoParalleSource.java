package com.flyex.streaming.custormSource;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

public class UseMyNoParalleSource {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //获取数据源
        DataStreamSource<Long> streamSource = env.addSource(new MyNoParalleSource());

        SingleOutputStreamOperator<Long> mapped = streamSource.map(new MapFunction<Long, Long>() {
            @Override
            public Long map(Long value) throws Exception {
                System.out.println("接收到的数据为：" + value);
                return value;
            }
        });

        SingleOutputStreamOperator<Long> result = mapped.timeWindowAll(Time.seconds(2)).sum(0);

        result.print().setParallelism(1);

        env.execute("UseMyNoParalleSource");

    }
}
