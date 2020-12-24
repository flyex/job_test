package com.flyex.streaming.custormSource;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

public class MyRichParallelSource extends RichParallelSourceFunction<Long> {

    private long count = 1L;

    private boolean isRunning = true;

    /**
     * 主要的方法
     * 启动一个source
     * 大部分情况下，都需要在这个run方法中实现一个循环，这样就可以循环产生数据了
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void run(SourceContext<Long> ctx) throws Exception {
        while(isRunning){
            ctx.collect(count);
            count++;
            //每秒产生一条数据
            Thread.sleep(1000);
        }
    }

    /**
     * 取消一个cancel的时候会调用的方法
     *
     */
    @Override
    public void cancel() {
        isRunning = false;
    }

    /*
        该方法只会在第一次运行时执行
        实现获取链接的代码
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        System.out.println("open source url");
        super.open(parameters);
    }

    /*
        关闭连接
     */
    @Override
    public void close() throws Exception {
        super.close();
    }
}
