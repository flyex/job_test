package com.flyex.source;

import org.apache.commons.io.FileUtils;
import org.apache.flume.Context;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TailFileSource extends AbstractSource implements EventDrivenSource, Configurable {
    private static final Logger logger = LoggerFactory.getLogger(TailFileSource.class);

    private String filePath;
    private String charset;
    private String posiFile;
    private Long interval;
    private ExecutorService executor;
    private FileRunnable fileRunnable;

    @Override
    public void configure(Context context) {
        filePath = context.getString("filePath");
        charset = context.getString("charset", "UTF-8");
        posiFile = context.getString("posiFile");
        interval = context.getLong("interval", 1000L);
    }

    @Override
    public synchronized void start() {
        executor = Executors.newSingleThreadExecutor();
        fileRunnable = new FileRunnable(filePath, posiFile, interval, charset, getChannelProcessor());
        executor.submit(fileRunnable);
        super.start();
    }

    @Override
    public synchronized void stop() {
        fileRunnable.setFlag(false);
        executor.shutdown();
        while (!executor.isTerminated()){
            logger.debug("waiting for executor service stop");
            try {
                executor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.error("stop app error",e);
            }
        }
        super.stop();
    }

    private static class FileRunnable implements Runnable {
        private Long interval;
        private String charset;
        private ChannelProcessor channelProcessor;
        private long offset;
        private RandomAccessFile raf;
        private boolean flag = true;
        private File positionFile;
        //初始化开始读文件的准备工作
        private FileRunnable(String filePath, String posiFile, Long interval, String charset, ChannelProcessor channelProcessor){
            this.interval = interval;
            this.charset = charset;
            this.channelProcessor = channelProcessor;

            positionFile = new File(posiFile);
            if (!positionFile.exists()){
                try {
                    positionFile.createNewFile();
                }catch (IOException e){
                    logger.error("create position file error",e);
                }
            }
            //读取偏移量
            try {
                String offsetString = FileUtils.readFileToString(positionFile);
                if (offsetString != null && !"".equals(offsetString)){
                    offset = Long.parseLong(offsetString);
                }
                //设定读取位置的偏移量
                raf = new RandomAccessFile(filePath, "r");
                raf.seek(offset);
            }catch (IOException e){
                logger.error("read position file error",e);
            }
        }

        @Override
        public void run() {
            while (flag){
                try {
                    String line = raf.readLine();

                    if (line!= null){
                        line = new String(line.getBytes("ISO-8859-1"),charset);
                        //将数据发给channel
                        channelProcessor.processEvent(EventBuilder.withBody(line.getBytes()));
                        offset = raf.getFilePointer();
                        FileUtils.writeStringToFile(positionFile,offset+"");
                    }else {
                        Thread.sleep(interval);
                    }
                }catch (IOException e){
                    logger.error("read log file error",e);
                } catch (InterruptedException e) {
                    logger.error("read file thread interrupted",e);
                }
            }
        }

        private void setFlag(boolean flag){
            this.flag = flag;
        }
    }
}
