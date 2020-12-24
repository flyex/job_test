package project;

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
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class JsonTest {
    public static class DataCleanMapper extends Mapper<LongWritable, Text,Text, NullWritable>{

        Text k = null;
        NullWritable v = null;
        SimpleDateFormat sdf = null;
        MultipleOutputs<Text,NullWritable> mos = null;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {

            k = new Text();
            v = NullWritable.get();
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mos = new MultipleOutputs<Text, NullWritable>(context);
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            JSONObject jsonObj = JSON.parseObject(value.toString());

            JSONObject headerObj = jsonObj.getJSONObject("header");

            k.set(JsonToStringUtil.toString(headerObj));
            if ("android".equals(headerObj.getString("os_name"))){
                mos.write(k,v,"android/android");
            }else {
                mos.write(k,v,"ios/ios");
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            mos.close();
        }
    }

    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(JsonTest.class);

        job.setMapperClass(DataCleanMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);

        FileInputFormat.setInputPaths(job,new Path("D:\\testDataJson\\multipleOutputData"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\noUse"));

        job.waitForCompletion(true);
    }
}
