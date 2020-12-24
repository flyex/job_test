package broadcastAndAccumulator

import kafka.consumer.ConsumerConfig
import org.apache.spark.{Accumulator, SparkConf, SparkContext}
import org.apache.spark.broadcast.Broadcast


object BAA {

  private var broadcastValue: Broadcast[List[String]] = null
  private var accumulatorValue: Accumulator[Int] = null

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[3]").setAppName("BAA")
    val sc = new SparkContext(conf)
    sc.setCheckpointDir("D:\\noUse")

    //注册广播变量
    broadcastValue = sc.broadcast(List("xiaoli", "xiaoming", "apple"))
    //注册累加器
    accumulatorValue = sc.accumulator(0, "accumulator")

    val data = sc.parallelize(List("dog", "cat", "apple", "apple", "xiaoli"))

    val wAo = data.flatMap(_.split(",", -1))
      .map((_, 1))
      .reduceByKey(_ + _)

    val res = wAo.filter(record => {
      if (broadcastValue.value.contains(record._1)) {
        accumulatorValue.add(record._2)
        true
      } else {
        false
      }
    })

    //注意累加器陷阱
    /**
      * spark中的一系列transform操作会构成一串长的任务链，此时需要通过一个action操作来触发，
      accumulator也是一样。因此在一个action操作之前，你调用value方法查看其数值，肯定是没有任何变化的。
      如果程序中有两次 action操作，就会触发两次transform操作，相应地，累加器就会加两次

      累加器陷阱解决办法：
      将任务之间的依赖关系切断，再次执行action操作就可以了
      res.cache()
      res.localCheckpoint()
    */

//    res.localCheckpoint()
//    res.collect()
    println(accumulatorValue.name + "累加器的值:" + accumulatorValue.value)

    //由于上面已将res persist(),以下输出不需要重新计算rdd的任务链，顾累加器不会重新计算
    res.foreachPartition(itr => {
      itr.foreach(rdd => {
        print(rdd)
        //println(Thread.currentThread().getName+ "的累加器值："+ accumulatorValue.localValue)
      })
    })
    res.foreachPartition(itr => {
      itr.foreach(rdd => {
        print("Aa")
        //println(Thread.currentThread().getName+ "的累加器值："+ accumulatorValue.localValue)
      })
    })

    println()
    println(accumulatorValue.name + "累加器的值:" + accumulatorValue.value)

  }

}
