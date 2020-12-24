package com.flyex.batch.setApi;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.accumulators.Accumulator;
import org.apache.flink.api.common.accumulators.IntCounter;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.configuration.Configuration;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class ApiAccumulator {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<Integer> data = env.fromElements(1, 2, 3, 4);

        MapOperator<Integer, Integer> result = data.map(new RichMapFunction<Integer, Integer>() {

            //定义map方法的累加器变量
            private IntCounter mapTimes = new IntCounter();
            //定义open方法的累加器变量
            private IntCounter openTimes = new IntCounter();

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                //注册累加器
                getRuntimeContext().addAccumulator("mapTimes", mapTimes);
                getRuntimeContext().addAccumulator("openTimes", openTimes);
                //open累加器计数,每个task执行只会执行一次
                //如果并行度设置为4，该值就为4
                openTimes.add(1);
            }

            @Override
            public Integer map(Integer value) throws Exception {
                //map累加器计数,有多少value,就会累加多少次
                mapTimes.add(1);
                return value;
            }
        }).setParallelism(1);

        //result.print()不能使用,因为env.execute方法需要一个sink
        result.writeAsText("D:\\testDataOut\\flinkOut\\out");

        JobExecutionResult jobResult = env.execute("ad");

        //获取到累加器
//        int mapTimes = jobResult.getAccumulatorResult("mapTimes");
//        int openTimes = jobResult.getAccumulatorResult("openTimes");
//        System.out.println("mapTimes:"+mapTimes+"\nopenTimes:"+openTimes);

        Map<String, Object> results = jobResult.getAllAccumulatorResults();

        Set<Map.Entry<String, Object>> entrySet = results.entrySet();

        for (Map.Entry<String,Object> entry : entrySet){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

    }
}
