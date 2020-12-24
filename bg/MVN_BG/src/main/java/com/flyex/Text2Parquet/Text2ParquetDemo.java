package com.flyex.Text2Parquet;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.hadoop.mapred.DeprecatedParquetOutputFormat;
import org.apache.parquet.schema.MessageTypeParser;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Text2ParquetDemo {

    public static class T2PMapper extends MapReduceBase implements Mapper<LongWritable, Text,Text, NullWritable> {
        Text text = new Text();

        public void map(LongWritable longWritable, Text value, OutputCollector<Text, NullWritable> output, Reporter reporter) throws IOException {
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);

            while (tokenizer.hasMoreTokens()){
                text.set(tokenizer.nextToken());
                output.collect(text,NullWritable.get());

            }
        }
    }

    public static class T2Reducer extends MapReduceBase implements Reducer<Text,NullWritable, Void,Group>{

        private SimpleGroupFactory factory;

        public void reduce(Text text, Iterator<NullWritable> iterator, OutputCollector<Void,Group> output, Reporter reporter) throws IOException {
            String[] words = text.toString().split(",");
            Group group = factory.newGroup()
                    .append("id", words[0])
                    .append("name", words[1]);
            output.collect(null,group);
        }

        @Override
        public void configure(JobConf job) {
            factory = new SimpleGroupFactory(GroupWriteSupport.getSchema(job));
        }
    }

    public static void main(String[] args) throws IOException {

        JobConf conf = new JobConf(Text2ParquetDemo.class);
        conf.setJobName("to2parquet");

        String writeSchema = "message example {\n" +
                "required binary id;\n" +
                "required binary name;\n" +
                "}";

        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(NullWritable.class);

        conf.setOutputKeyClass(NullWritable.class);
        conf.setOutputValueClass(Group.class);

        conf.setMapperClass(T2PMapper.class);
        conf.setReducerClass(T2Reducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(DeprecatedParquetOutputFormat.class);

        FileInputFormat.setInputPaths(conf,new Path("D:\\testData\\text"));
        DeprecatedParquetOutputFormat.setWriteSupportClass(conf,GroupWriteSupport.class);
        GroupWriteSupport.setSchema(MessageTypeParser.parseMessageType(writeSchema), conf);

        DeprecatedParquetOutputFormat.setOutputPath(conf,new Path("D:\\testDataOut\\parquet"));

        JobClient.runJob(conf);
    }
}
