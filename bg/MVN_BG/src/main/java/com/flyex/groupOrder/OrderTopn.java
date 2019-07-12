package com.flyex.groupOrder;

import com.my.flow.FlowBean;
import com.my.flow.Total;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class OrderTopn {
    public static class OrderMapper extends Mapper<LongWritable, Text,Text,OrderBean>{

        Text k = new Text();
        OrderBean orderBean = new OrderBean();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] words = value.toString().split(",");
            k.set(words[0]);
            orderBean.set(words[0],words[1],words[2],Float.parseFloat(words[3]),Integer.parseInt(words[4]));
            context.write(k,orderBean);
        }
    }

    public static class OrderReducer extends Reducer<Text,OrderBean,OrderBean, NullWritable>{


        @Override
        protected void reduce(Text key, Iterable<OrderBean> values, Context context) throws IOException, InterruptedException {
            Configuration conf = context.getConfiguration();
            ArrayList<OrderBean> arrayList = new ArrayList<OrderBean>();
            int topn = conf.getInt("top_n",3);

            for (OrderBean ob:values){
                OrderBean order = new OrderBean();
                order.set(ob.getOrderId(), ob.getUserId(), ob.getPdtName(), ob.getPrice(), ob.getNumber());
                arrayList.add(order);
            }
            Collections.sort(arrayList);
            for (int i=0;i<topn;i++){
                context.write(arrayList.get(i),NullWritable.get());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.setInt("top_n",3);
        Job job = Job.getInstance(conf);

        job.setJarByClass(OrderTopn.class);

        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(OrderBean.class);
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(2);

        FileInputFormat.setInputPaths(job,new Path("D:\\hdpTest\\wordcount\\input\\input___"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hdpTest\\wordcount\\output\\output___"));

        job.waitForCompletion(true);
    }
}
