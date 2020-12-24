package com.flyex.demo.data_clean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class AppDataClean {

    public static class AppDataCleanMapper extends Mapper<LongWritable, Text, Text, NullWritable>{

        Text k = null;
        NullWritable n = null;
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            k = new Text();
            n = NullWritable.get();
            Configuration conf = context.getConfiguration();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();

            JSONObject object = JSON.parseObject(line);

            JSONObject header = object.getJSONObject("header");

            String city = header.getString("city");
            String os_name = header.getString("os_name");
            String language = header.getString("language");

            String device_model = "";
            if (header.getString("device_model").contains(",")){
               device_model =  header.getString("device_model").replaceAll(",","-");
            }else {
                device_model = header.getString("device_model");
            }

            k.set(device_model+","+os_name+","+city+","+language);

            context.write(k,n);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //conf.addResource("");

        Job job = Job.getInstance(conf);

        job.setJarByClass(AppDataClean.class);
        job.setMapperClass(AppDataCleanMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);

    }

}
