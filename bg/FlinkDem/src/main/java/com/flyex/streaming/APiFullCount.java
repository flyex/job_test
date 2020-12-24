package com.flyex.streaming;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Collections;

/*
    全量聚合
 */
public class APiFullCount {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> data = env.socketTextStream("hdp-01", 4000, '\n');

        SingleOutputStreamOperator<Tuple2<Integer, Long>> mapped = data.map(new MapFunction<String, Tuple2<Integer, Long>>() {
            @Override
            public Tuple2<Integer, Long> map(String value) throws Exception {
                return new Tuple2<>(1, Long.valueOf(value));
            }
        });
        
        mapped.keyBy(0)
                .timeWindow(Time.seconds(5))
                .process(new ProcessWindowFunction<Tuple2<Integer, Long>, String, Tuple, TimeWindow>() {
                    @Override
                    public void process(Tuple tuple, Context context, Iterable<Tuple2<Integer, Long>> elements, Collector<String> out) throws Exception {
                        System.out.println("开始执行process.......");
                        long count = 0;
                        ArrayList<Long> nums = new ArrayList<>();
                        for (Tuple2<Integer, Long> ele:elements){
                            count ++;
                            nums.add(ele._2);
                        }
                        System.out.println("该批次最大值："+Collections.max(nums)+"||windows:"+context.window()
                                + "||count:"+count );
                    }
                }).print();

        env.execute("as");


    }
}
