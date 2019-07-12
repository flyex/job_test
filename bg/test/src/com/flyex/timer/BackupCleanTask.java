package com.flyex.timer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.optim.SimplePointChecker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimerTask;

public class BackupCleanTask extends TimerTask {

    @Override
    public void run() {
        try{
            Properties props = PropertyLazy.getProps();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
            long now = new Date().getTime();

            File backupDir = new File(props.getProperty(Constants.LOG_BACKUP_BASE_DIR));
            File[] bf = backupDir.listFiles();
            for (File f:bf){
                long before = sdf.parse(f.getName()).getTime();
                if (now-before>(Integer.parseInt(props.getProperty(Constants.LOG_BACKUP_TIMEOUT)))*60*60*1000L){
                    FileUtils.deleteDirectory(f);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
