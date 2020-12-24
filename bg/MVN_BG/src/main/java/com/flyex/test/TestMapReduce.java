package com.flyex.test;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TestMapReduce {

    public static class test_1 extends Mapper<LongWritable, Text,Text,Text>{


        protected void map(LongWritable key, Text value, OutputCollector<Text,Text> output, Reporter reporter) throws IOException, InterruptedException {

            String filePath = ((FileSplit) reporter.getInputSplit()).getPath().toString();

        }
    }

}
