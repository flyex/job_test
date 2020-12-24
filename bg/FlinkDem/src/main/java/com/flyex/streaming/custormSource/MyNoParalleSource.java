package com.flyex.streaming.custormSource;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class MyNoParalleSource implements SourceFunction<Long> {

    private Long i = 0L;
    private boolean isRunning = true;
    /*
        run中循环产生数据
    */
    @Override
    public void run(SourceContext<Long> ctx) throws Exception {
        while (isRunning){
            ctx.collect(i);
            i++;
            Thread.sleep(1000);
        }
    }

    /*
        取消一个任务时会调用cancel方法
     */
    @Override
    public void cancel() {
        isRunning = false;
    }
}
