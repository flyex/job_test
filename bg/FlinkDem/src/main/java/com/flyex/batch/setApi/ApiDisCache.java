package com.flyex.batch.setApi;

import org.apache.commons.io.FileUtils;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.configuration.Configuration;

import java.io.File;
import java.util.List;

public class ApiDisCache {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        env.registerCachedFile("D:\\testDataOut\\flinkOut\\out","cache");

        DataSource<Integer> data = env.fromElements(1, 2, 3, 4);

        DataSet<Integer> result = data.map(new RichMapFunction<Integer, Integer>() {

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                File file = getRuntimeContext().getDistributedCache().getFile("cache");
                List<String> fs = FileUtils.readLines(file);
                for (String lines : fs) {
                    System.out.println(lines);
                }
            }

            @Override
            public Integer map(Integer value) throws Exception {
                return value;
            }
        });

        result.print();

    }
}
