package com.flyex.timer;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.TimerTask;

public class CollectTask extends TimerTask {
    @Override
    public void run() {
        try {
            Properties props = PropertyLazy.getProps();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
            String day = sdf.format(new Date());

            File srcdir = new File(props.getProperty(Constants.LOG_SOURCE_DIR));
            File[] listfiles = srcdir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.startsWith(props.getProperty(Constants.LOG_LEGAL_PREFIX))){
                        return true;
                    }
                    return false;
                }
            });
            System.out.println(Arrays.toString(listfiles));

            File toUploadDir = new File(props.getProperty(Constants.LOG_TOUPLOAD_DIR));

            for (File f:listfiles){
                FileUtils.moveFileToDirectory(f,toUploadDir,true);
            }
            FileSystem fs = FileSystem.get(new URI(props.getProperty(Constants.HDFS_URI)),new Configuration(),"root");
            File[] toUploadFiles = toUploadDir.listFiles();
            //检查hdfs中的日期目录是否存在
            Path hdfsPath = new Path(props.getProperty(Constants.HDFS_DEST_BASE_DIR)+day);
            if (!fs.exists(hdfsPath)){
                fs.mkdirs(hdfsPath);
            }

            //检查本地目录是否存在
            File backupDir = new File(props.getProperty(Constants.LOG_BACKUP_BASE_DIR)+day+"/");
            if (!backupDir.exists()){
                backupDir.mkdirs();
            }

            for (File f:toUploadFiles){
                Path hdfsPath2 = new Path(hdfsPath+ "/"+UUID.randomUUID()+props.getProperty(Constants.HDFS_FILE_SUFFIX));
                fs.copyFromLocalFile(new Path(f.getAbsolutePath()),hdfsPath2);
                //移动至本地备份目录
                FileUtils.moveFileToDirectory(f,backupDir,true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
