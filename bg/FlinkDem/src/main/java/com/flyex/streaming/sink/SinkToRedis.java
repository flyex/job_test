package com.flyex.streaming.sink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

public class SinkToRedis {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> stream = env.socketTextStream("hdp-01", 4000, "\n");

        SingleOutputStreamOperator<Tuple2<String, String>> l_words = stream.map(new MapFunction<String, Tuple2<String, String>>() {
            @Override
            public Tuple2<String, String> map(String value) throws Exception {
                return new Tuple2<>("l_words", value);
            }
        });

        FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("hdp-04").setPort(6379).build();

        RedisSink<Tuple2<String,String>> redisSink = new RedisSink<>(conf,new MyRedisMapper());

        l_words.addSink(redisSink);

        env.execute("asd");

    }

    public static class MyRedisMapper implements RedisMapper<Tuple2<String,String>>{
        @Override
        public String getKeyFromData(Tuple2<String, String> stringStringTuple2) {
            return stringStringTuple2.f0;
        }

        @Override
        public String getValueFromData(Tuple2<String, String> stringStringTuple2) {
            return stringStringTuple2.f1;
        }

        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.LPUSH);
        }
    }
}
