package com.flyex.batch.setApi;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiBroadcast {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //模拟要广播的数据
        ArrayList<Tuple2<String,Integer>> broadInfo = new ArrayList<>();
        broadInfo.add(new Tuple2<>("zs",15));
        broadInfo.add(new Tuple2<>("ls",16));
        broadInfo.add(new Tuple2<>("ww",18));

        //将要广播的原始数据转换为HashMap类型
        DataSource<Tuple2<String, Integer>> brTuple = env.fromCollection(broadInfo);

        MapOperator<Tuple2<String, Integer>, HashMap<String, Integer>> brMapped = brTuple.map(new MapFunction<Tuple2<String, Integer>, HashMap<String, Integer>>() {
            @Override
            public HashMap<String, Integer> map(Tuple2<String, Integer> value) throws Exception {
                HashMap<String, Integer> session = new HashMap<>();
                session.put(value.f0, value.f1);
                return session;
            }
        });

        DataSet<String> person = env.fromElements("zs", "ls", "ww");

        //要使用广播变量必须使用RichMapFunction，在open里获取广播变量
        DataSet<String> result = person.map(new RichMapFunction<String, String>() {

            List<HashMap<String, Integer>> br = new ArrayList<HashMap<String, Integer>>();
            HashMap<String, Integer> personInfo = new HashMap<>();

            /*
                获取广播变量类型为List<HashMap<String,Integer>>
                将其全部放入到HashMap供后续使用
                open方法只会执行一次
             */
            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                br = getRuntimeContext().getBroadcastVariable("br");
                for (HashMap map : br) {
                    personInfo.putAll(map);
                }
            }
            //map.getValue获取值
            @Override
            public String map(String value) throws Exception {
                return value + ":" + personInfo.get(value);
            }

        })./*必须将广播操作放在每个map方法的后面，否则无法使用*/
          withBroadcastSet(brMapped, "br");

        result.print();

    }
}
