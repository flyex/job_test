package com.flyex.combineFileInputFormat;

import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileRecordReader;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;

import java.io.IOException;

public class CUstomCFIF extends CombineFileInputFormat<LongWritable, Text> {

    public CUstomCFIF(){
        super();
        setMaxSplitSize(128*1024);
    }

    @Override
    protected boolean isSplitable(JobContext context, Path file) {
        return false;
    }

    @Override
    public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException {
        return new CombineFileRecordReader<LongWritable, Text>((CombineFileSplit) split, context, CustomRecordReader.class);
    }

    @Override
    public org.apache.hadoop.mapred.RecordReader<LongWritable, Text> getRecordReader(org.apache.hadoop.mapred.InputSplit inputSplit, JobConf jobConf, Reporter reporter) throws IOException {
        return null;
    }

    public void test(){
        String s1 = new String();
    }
}
