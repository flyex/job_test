package com.flyex.joinOU;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;

public class OUJoin {
    public static class OUMapper extends Mapper<LongWritable, Text,Text,OUBean>{
        String fileName = null;
        OUBean ouBean = new OUBean();
        Text k = new Text();
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            InputSplit inputSplit = context.getInputSplit();
            FileSplit fileSplit = (FileSplit) inputSplit;
            fileName = fileSplit.getPath().getName();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] ou = value.toString().split(",");
            if (fileName.startsWith("order")){
                ouBean.set(ou[0],ou[1],"NULL",-1,"NULL","order");
            }else {
                ouBean.set("NULL",ou[0],ou[1],Integer.parseInt(ou[2]),ou[3],"user");
            }
            k.set(ouBean.getUserId());
            context.write(k,ouBean);
        }
    }

    public static class OUReducer extends Reducer<Text,OUBean,OUBean, NullWritable>{
        @Override
        protected void reduce(Text key, Iterable<OUBean> values, Context context) throws IOException, InterruptedException {
            OUBean uBean = null;
            ArrayList<OUBean> orderList = new ArrayList<OUBean>();
            try {
                for (OUBean o:values){
                    if ("order".equals(o.getTableName())){
                        OUBean oBean = new OUBean();
                        BeanUtils.copyProperties(oBean,o);
                        orderList.add(oBean);
                    }else {
                        uBean = new OUBean();
                        BeanUtils.copyProperties(uBean,o);
                    }
                }

                for (OUBean ou:orderList){
                    ou.setUserName(uBean.getUserName());
                    ou.setUserAge(uBean.getUserAge());
                    ou.setUserFriend(uBean.getUserFriend());

                    context.write(ou,NullWritable.get());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(OUJoin.class);
        job.setMapperClass(OUMapper.class);
        job.setReducerClass(OUReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(OUBean.class);
        job.setOutputKeyClass(OUBean.class);
        job.setOutputValueClass(NullWritable.class);


        job.setNumReduceTasks(1);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\input\\intput_ouJoin"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output_ouJoin"));

        job.waitForCompletion(true);
    }
}
