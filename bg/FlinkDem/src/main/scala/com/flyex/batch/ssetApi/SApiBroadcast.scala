package com.flyex.batch.ssetApi

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

import scala.collection.mutable.ListBuffer

object SApiBroadcast {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    val baseInfo = ListBuffer[Tuple2[String,Int]]()
    baseInfo.append(Tuple2("zs",12))
    baseInfo.append(("ls",14))
    baseInfo.append(("ww",11))

    import org.apache.flink.api.scala._
    //baseInfo -> HashMap[]格式
    val baseData = env.fromCollection(baseInfo)
    val baseMapped = baseData.map(tp => {
      Map(tp._1 -> tp._2)
    })

    val personData = env.fromElements("zs","ls","ww")

    val result: DataSet[String] = personData.map(new RichMapFunction[String,String] {

      //注意使用java的list
      var br: java.util.List[Map[String,Int]] = null
      var brMap = Map[String,Int]()

      override def open(parameters: Configuration): Unit = {
        super.open(parameters)
        br = getRuntimeContext.getBroadcastVariable[Map[String,Int]]("br")
        //获取到迭代器
        val brItr = br.iterator()
        while(brItr.hasNext){
          brMap = brMap .++ (brItr.next())
        }
      }

      override def map(value: String): String = {
        value+":"+brMap.get(value).get
      }
    }).withBroadcastSet(baseMapped,"br")

    result.print()

  }

}
