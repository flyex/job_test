package com.flyex.batch;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class BatchWordCountJava {

    public static void main(String[] args) throws Exception {

        String inputPath = "D:\\testData";
        String outputPath = "D:\\testDataOut\\wcOut\\out";

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> lines = env.readTextFile(inputPath);

        DataSet<Tuple2<String, Integer>> result = lines.flatMap(new Tokenizer()).groupBy(0).sum(1);

        //func2
        AggregateOperator<Tuple2<String, Integer>> result2 = lines.flatMap(new Tokenizer1()).map(new MapFunction<Tuple1<String>, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(Tuple1<String> stringTuple1) throws Exception {
                String f0 = stringTuple1.f0;
                return new Tuple2<String, Integer>(f0, 1);
            }
        }).groupBy(0).sum(1);

        result.writeAsCsv(outputPath,"\n","\001").setParallelism(1);

        env.execute("batch word count");

    }

    public static class Tokenizer implements FlatMapFunction<String, Tuple2<String,Integer>>{
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] fields = value.toLowerCase().split(" ");
            for (String word :
                    fields) {
                if (word.length()>0){
                    out.collect(new Tuple2<String,Integer>(word,1));
                }
            }
        }
    }

    public static class Tokenizer1 implements FlatMapFunction<String, Tuple1<String>>{
        @Override
        public void flatMap(String value, Collector<Tuple1<String>> out) throws Exception {
            String[] fields = value.split(",");
            for (String word : fields){
                out.collect(new Tuple1<String>(word));
            }

        }
    }
}
