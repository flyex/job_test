package com.flyex.friend.avroTest;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroMapper;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyValueOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AvroUseInMR {

    private static final Schema SCHEMA = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"StudentRecord\",\"doc\":\"A student\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"},{\"name\":\"sex\",\"type\":\"string\"}]}");

    public static class avroMapper extends Mapper<LongWritable, Text, AvroKey<Integer>, AvroValue<GenericRecord>>{

        private GenericRecord genericRecord = new GenericData.Record(SCHEMA);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String data = value.toString();
            String[] datas = data.split(",", -1);
            genericRecord.put("name",datas[0]);
            genericRecord.put("age",datas[1]);
            genericRecord.put("sex",datas[2]);

            context.write(new AvroKey<Integer>(Integer.valueOf(datas[1])),new AvroValue<GenericRecord>(genericRecord));
        }
    }

    public static class AvroReducer extends Reducer<AvroKey<Integer>, AvroValue<GenericRecord>,AvroKey<Integer>, AvroValue<GenericRecord>>{

        @Override
        protected void reduce(AvroKey<Integer> key, Iterable<AvroValue<GenericRecord>> values, Context context) throws IOException, InterruptedException {
            for (AvroValue<GenericRecord> value : values){
                context.write(new AvroKey<>(key.datum()),value);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        conf.setBoolean(Job.MAPREDUCE_JOB_USER_CLASSPATH_FIRST, true);

        Job job = Job.getInstance(conf);

        job.setJarByClass(AvroUseInMR.class);

        job.setMapperClass(avroMapper.class);
        job.setInputFormatClass(TextInputFormat.class);
        AvroJob.setMapOutputKeySchema(job,Schema.create(Schema.Type.INT));
        AvroJob.setMapOutputValueSchema(job,SCHEMA);


        job.setReducerClass(AvroReducer.class);
        job.setOutputFormatClass(AvroKeyValueOutputFormat.class);
        AvroJob.setOutputKeySchema(job, Schema.create(Schema.Type.INT));
        AvroJob.setOutputValueSchema(job, SCHEMA);


        FileInputFormat.addInputPath(job,new Path("file:\\D:\\testData\\stuinfo"));
        FileOutputFormat.setOutputPath(job,new Path("file:\\D:\\testDataOut\\avroOut"));

        job.setNumReduceTasks(2);
        job.waitForCompletion(true);


    }

}
