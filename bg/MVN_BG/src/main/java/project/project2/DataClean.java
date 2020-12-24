package project.project2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/*
将一行text 转换成有规律的字符串
 */
public class DataClean {

    public static class DataCleanMap extends Mapper<LongWritable, Text,Text, NullWritable> {
        Text k = null;
        NullWritable v = null;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            k = new Text();
            v = NullWritable.get();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split("[/]");

            String subject = fields[2].split("[.]")[0];

            k.set(subject+","+fields[3]);

            context.write(k,v);

        }

    }

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(DataClean.class);

        job.setMapperClass(DataCleanMap.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job,new Path("hdfs://hdp-01:9000/favTeacher"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://hdp-01:9000/favTeacher_cleaned"));

        job.waitForCompletion(true);

    }
}
