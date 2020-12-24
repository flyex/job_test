package com.flyex.batch.setApi;

import org.apache.flink.api.common.functions.FlatJoinFunction;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.util.Collector;

import java.util.ArrayList;

public class ApiKindOFJoin {

    public static void main(String[] args) throws Exception {

        ArrayList<Tuple2<Integer,String>> data1 = new ArrayList<>();
        data1.add(new Tuple2<>(2,"zs"));
        data1.add(new Tuple2<>(3,"ll"));
        data1.add(new Tuple2<>(4,"ww"));

        ArrayList<Tuple2<Integer,String>> data2 = new ArrayList<>();
        data2.add(new Tuple2<>(2,"sh"));
        data2.add(new Tuple2<>(3,"sz"));
        data2.add(new Tuple2<>(4,"bj"));

        ArrayList<Tuple2<Integer,Integer>> data3 = new ArrayList<>();
        data3.add(new Tuple2<>(2,12));
        data3.add(new Tuple2<>(3,14));
        data3.add(new Tuple2<>(5,17));


        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<Tuple2<Integer, String>> s1 = env.fromCollection(data1);
        DataSource<Tuple2<Integer, String>> s2 = env.fromCollection(data2);
        DataSource<Tuple2<Integer, Integer>> s3 = env.fromCollection(data3);


        s1.join(s2).where(0)
                .equalTo(0)
                .with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, String>, Tuple3<Integer,String,String>>() {
                    @Override
                    public Tuple3<Integer, String, String> join(Tuple2<Integer, String> first, Tuple2<Integer, String> second) throws Exception {
                        return new Tuple3<>(first.f0,first.f1,second.f1);
                    }
                })/*.print()*/;

        s1.leftOuterJoin(s3).where(0)
                .equalTo(0)
                .with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, Integer>, Tuple3<Integer,String,Integer>>() {
                    @Override
                    public Tuple3<Integer, String, Integer> join(Tuple2<Integer, String> first, Tuple2<Integer, Integer> second) throws Exception {
                         if (second == null){
                             return new Tuple3<Integer, String, Integer>(first.f0,first.f1,0);
                         }else {
                             return new Tuple3<>(first.f0,first.f1,second.f1);
                         }
                    }
                })/*.print()*/;

        s1.fullOuterJoin(s3).where(0)
                .equalTo(0)
                .with(new JoinFunction<Tuple2<Integer, String>, Tuple2<Integer, Integer>, Tuple3<Integer,String,Integer>>() {
                    @Override
                    public Tuple3<Integer, String, Integer> join(Tuple2<Integer, String> first, Tuple2<Integer, Integer> second) throws Exception {
                        if (first == null){
                            return new Tuple3<>(second.f0,"null",second.f1);
                        }else if (second == null){
                            return new Tuple3<>(first.f0,first.f1,0);
                        }else {
                            return new Tuple3<>(first.f0,first.f1,second.f1);
                        }
                    }
                })/*.print()*/;


        s1.cross(s3).print();
        //((2,zs),(2,12))
        //((2,zs),(3,14))
        //((2,zs),(5,17))
        //((3,ll),(2,12))
        //((3,ll),(3,14))
        //((3,ll),(5,17))
        //((4,ww),(2,12))
        //((4,ww),(3,14))
        //((4,ww),(5,17))

    }

}
