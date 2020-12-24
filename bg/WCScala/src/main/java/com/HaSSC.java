package com;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function0;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

public class HaSSC {

    public String x = "aa";

    public void print(){
        System.out.println(x);
    }


    public static void main(String[] args) throws Exception{

        Function0<JavaStreamingContext> contextFactory = new Function0<JavaStreamingContext>() {

            public JavaStreamingContext call(){
                SparkConf conf = new SparkConf();
                conf.setAppName("");
                conf.setMaster("");
                JavaStreamingContext jssc = new JavaStreamingContext(conf, new Duration(2000));
                JavaDStream<String> lines = jssc.socketTextStream("",1999);
                JavaDStream<Long> dsCount = lines.countByWindow(new Duration(24 * 60 * 60), new Duration(2000));

                jssc.checkpoint("file:///d:/asd");

                return jssc;
            }
        };

        JavaStreamingContext context = JavaStreamingContext.getOrCreate("file:", contextFactory);

    }

}
