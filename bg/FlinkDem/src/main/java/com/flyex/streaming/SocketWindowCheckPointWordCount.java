package com.flyex.streaming;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class SocketWindowCheckPointWordCount {

    public static void main(String[] args) throws Exception {

        int port;
        try {
            ParameterTool parameterTool = ParameterTool.fromArgs(args);
            port = parameterTool.getInt("port");
        }catch (Exception e){
            System.err.println("no port set,Use default 4000 port");
            port = 4000;
        }

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //设置checkpoint
        env.enableCheckpointing(1000);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
        env.getCheckpointConfig().setCheckpointTimeout(60000);
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

        //设置state backend
        //state的store和checkpoint的位置由statebackend确定
        env.setStateBackend(new RocksDBStateBackend("hdfs://ns-ha/flink/checkpoints"));


        String hostname = "hdp-01";
        String delimiter = "\n";

        DataStreamSource<String> lines = env.socketTextStream(hostname, port, delimiter);

        SingleOutputStreamOperator<WordAndCount> result = lines.flatMap(new FlatMapFunction<String, WordAndCount>() {
            @Override
            public void flatMap(String s, Collector<WordAndCount> collector) throws Exception {
                String[] splits = s.split(" ");
                for (String word : splits) {
                    collector.collect(new WordAndCount(word, 1L));
                }
            }
        })./*uid("flat-map")*/keyBy("word").timeWindow(Time.seconds(2), Time.seconds(1))
                .sum("count");
//                .reduce(new ReduceFunction<WordAndCount>() {
//                    @Override
//                    public WordAndCount reduce(WordAndCount wordAndCount, WordAndCount t1) throws Exception {
//                        return new WordAndCount(wordAndCount.word, wordAndCount.count + t1.count);
//                    }
//                });

        result.print().setParallelism(1);

        //启动整个flink流程
        env.execute("Socket window count");

    }

    public static class WordAndCount{
        public String word;
        public long count;

        public WordAndCount() { }
        public WordAndCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return "{word:"+word+"\tcount:"+count+"}";
        }
    }
}
