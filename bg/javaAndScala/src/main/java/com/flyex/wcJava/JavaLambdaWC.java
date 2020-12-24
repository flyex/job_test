package com.flyex.wcJava;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class JavaLambdaWC {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("wc");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        JavaRDD<String> lines = jsc.textFile(args[0]);
        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
        JavaPairRDD<String, Integer> WDO = words.mapToPair(x -> new Tuple2<>(x, 1));

        JavaPairRDD<String, Integer> reduced = WDO.reduceByKey((a, b) -> a + b);
        JavaPairRDD<Integer, String> red = reduced.mapToPair(a -> a.swap());
        JavaPairRDD<Integer, String> sorted = red.sortByKey(false);

        JavaPairRDD<String, Integer> result = sorted.mapToPair(a -> a.swap());

        result.saveAsTextFile(args[1]);

        jsc.stop();

    }
}
