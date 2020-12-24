package com.hbase.hbase2mysql;

import com.hbase.hbase2mysql.utils.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlOutput extends OutputFormat<Text, Text> {

    private FileOutputCommitter committer = null;


    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        Connection conn = JDBCUtil.getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new MySQLDBRecordWriter(conn);
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {

    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        if (committer == null){
            Path output = getOutputPath(taskAttemptContext);
            committer = new FileOutputCommitter(output,taskAttemptContext);
        }
        return committer;
    }
    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null : new Path(name);
    }

    public static class MySQLDBRecordWriter extends RecordWriter<Text, Text> {

        private Connection conn = null;
        private PreparedStatement pre = null;

        //初始化连接器
        public MySQLDBRecordWriter(Connection conn) {
            this.conn = conn;
        }

        //往Mysql写数据核心方法
        @Override
        public void write(Text text, Text text2) throws IOException, InterruptedException {

            String sql = "insert into students (name,password) values (?,?);";

            String name = text2.toString();

            try {
                if (pre == null) {
                    pre = conn.prepareStatement(sql);
                }

                pre.setString(1, name.substring(5, name.length() - 1));
                pre.setString(2, text.toString());
                pre.execute();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            if (conn != null) {
                JDBCUtil.close(conn,pre,null);
            }
        }
    }
}
