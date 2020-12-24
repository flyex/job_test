package com.flyex.streaming.streamPartition;

import com.flyex.streaming.custormSource.MyNoParalleSource;
import com.flyex.streaming.custormSource.MyParallelSource;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class PWithMyPartition {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(3);
        DataStreamSource<Long> source = env.addSource(new MyNoParalleSource());

        SingleOutputStreamOperator<Tuple1<Long>> tupleData = source.map(new MapFunction<Long, Tuple1<Long>>() {
            @Override
            public Tuple1<Long> map(Long value) throws Exception {
                return new Tuple1<>(value);
            }
        });

        DataStream<Tuple1<Long>> partitioned = tupleData.partitionCustom(new MyPartition(), 0);

        SingleOutputStreamOperator<Long> result = partitioned.map(new MapFunction<Tuple1<Long>, Long>() {
            @Override
            public Long map(Tuple1<Long> value) throws Exception {
                System.out.println("当前分区的线程id为：" + Thread.currentThread().getId() + ",value:" + value);
                return value.getField(0);
            }
        });

        result.print().setParallelism(1);

        env.execute("ad");

    }
}
