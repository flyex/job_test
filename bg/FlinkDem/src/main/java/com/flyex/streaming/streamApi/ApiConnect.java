package com.flyex.streaming.streamApi;

import com.flyex.streaming.custormSource.MyNoParalleSource;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoFlatMapFunction;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.util.Collector;

public class ApiConnect {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Long> con1 = env.addSource(new MyNoParalleSource()).setParallelism(1);

        DataStreamSource<Long> con2 = env.addSource(new MyNoParalleSource()).setParallelism(1);

        //可以连接两个不同类型的流
        ConnectedStreams<Long, Long> connectStream = con1.connect(con2);

        SingleOutputStreamOperator<String> result = connectStream.flatMap(new CoFlatMapFunction<Long, Long, String>() {
            Long tmp = 0L;
            @Override
            public void flatMap1(Long value, Collector<String> out) throws Exception {
                out.collect(value+""+tmp);
            }

            @Override
            public void flatMap2(Long value, Collector<String> out) throws Exception {
                tmp = value;
            }
        });

        result.print().setParallelism(1);

        env.execute("aha");


    }
}
