package com.flyex.streaming.waterMark;

/*

 */

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;

public class WindowsWatermark1 {

    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setParallelism(1);

        DataStreamSource<String> data = env.socketTextStream("hdp-01", 4000, '\n');

        SingleOutputStreamOperator<Tuple2<Integer, Integer>> mapped = data.map(new MapFunction<String, Tuple2<Integer, Integer>>() {
            @Override
            public Tuple2<Integer, Integer> map(String value) throws Exception {
                return new Tuple2<>(1, Integer.valueOf(value));
            }
        });

        mapped.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<Tuple2<Integer, Integer>>() {

            long currentMaxTimeStamp = 0L;
            final Long maxOutofOrderness = 10000L;  //定义最大乱序时间

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            @Nullable
            @Override
            public Watermark getCurrentWatermark() {
                //获取当前水位线
                return new Watermark(currentMaxTimeStamp-maxOutofOrderness);
            }

            @Override
            public long extractTimestamp(Tuple2<Integer, Integer> element, long previousElementTimestamp) {
                return 0;
            }
        });


    }
}
