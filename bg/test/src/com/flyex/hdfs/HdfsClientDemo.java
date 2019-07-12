package com.flyex.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.net.URI;
import java.util.Arrays;

public class HdfsClientDemo {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        conf.set("dfs.replication","2");
        conf.set("dfs.blocksize","64m");

        FileSystem fs = FileSystem.get(new URI("hdfs://hdp-01:9000/"),conf,"root");

        fs.copyFromLocalFile(new Path("D:/BaiduNetdiskDownload/javaweb.zip"),new Path("/aaa"));

        fs.close();
    }
    FileSystem fs = null;
    @Before
    public void init() throws Exception{
        Configuration conf = new Configuration();

        conf.set("dfs.replication","2");
        conf.set("dfs.blocksize","64m");

        fs = FileSystem.get(new URI("hdfs://hdp-01:9000/"),conf,"root");
    }

    @Test
    public void createDir() throws Exception{
        fs.mkdirs(new Path("/xx/yy/zz/aa"));
        fs.close();
    }

    @Test
    public void testGet() throws Exception {
        fs.copyToLocalFile(new Path("/hadoop-2.8.5.tar.gz"),new Path("d:/test.tar.gz"));
        fs.close();
    }

    @Test
    public void testLs() throws Exception{
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/aaa"),true);

        while (iterator.hasNext()){
            LocatedFileStatus status = iterator.next();
            System.out.println("文件全路径："+status.getPath());
            System.out.println("块大小："+status.getBlockSize());
            System.out.println("文件长度："+status.getLen());
            System.out.println("副本数量："+status.getReplication());
            System.out.println("块信息："+ Arrays.toString(status.getBlockLocations()));

            System.out.println("--------------------------------");
        }
        fs.close();
    }

    //test seek
    @Test
    public void testRandomReadDate() throws Exception{
        FSDataInputStream in = fs.open(new Path("/anglababy"));

        in.seek(16);
        byte[] buf = new byte[14];
        in.read(buf);

        System.out.println(new String(buf));

        in.close();
        fs.close();
    }

    //write to hdfs
    @Test
    public void testWrite() throws Exception{
        FSDataOutputStream out = fs.create(new Path("/TM.txt"));

        FileInputStream in = new FileInputStream("D:/timeserver.txt");

        byte[] buf = new byte[1024];
        int read = 0;

        while((read = in.read(buf))!=-1){
            out.write(buf,0,read);
        }

        in.close();
        out.close();
        fs.close();
    }
}
