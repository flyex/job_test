package com.flyex.friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FriendHave {
    public static class FriendMapper extends Mapper<LongWritable, Text,Text,Text>{
        Text fri = new Text();
        Text users = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] people = value.toString().split(":");
            String[] friends = people[1].split(",");
            users.set(people[0]);
            for (String f:friends){
                fri.set(f);
                context.write(fri,users);
            }
        }
    }

    public static class FriendReducer extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text fri, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            ArrayList<String> userlist = new ArrayList<String>();
            Text users = new Text();
            for (Text user:values){
                userlist.add(user.toString());
            }
            Collections.sort(userlist);
            for (int i=0;i<userlist.size()-1;i++){
                for (int j=i+1;j<userlist.size();j++){
                    users.set(userlist.get(i)+"-"+userlist.get(j));
                    context.write(users,fri);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(FriendHave.class);
        job.setMapperClass(FriendMapper.class);
        job.setReducerClass(FriendReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(2);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\input\\input_friend"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output_friend"));

        job.waitForCompletion(true);
    }
}
