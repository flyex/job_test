package com.flyex.batch.ssetApi

import java.util

import org.apache.flink.api.scala.ExecutionEnvironment

import scala.collection.mutable.ListBuffer

object SApiJoin {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment
    import org.apache.flink.api.scala._

    val data1 = ListBuffer[Tuple2[Int,String]]()
    val data2 = ListBuffer[Tuple2[Int,String]]()

    data1.append((1,"zs"))
    data1.append((2,"ls"))
    data1.append((3,"ww"))
    data2.append((1,"beijing"))
    data2.append((2,"shanghai"))
    data2.append((4,"guangzhou"))

    val s1 = env.fromCollection(data1)
    val s2 = env.fromCollection(data2)

    s1.leftOuterJoin(s2).where(0)
      .equalTo(0)
      .apply((left,right) =>{
        if (right == null){
          (left._1,left._2,"null")
        }else{
          (left._1,left._2,right._2)
        }
      }).print()

    s1.join(s2).where(0)
      .equalTo(0)
      .apply((left,right) =>{
        (left._1,left._2,right._2)
      }).print()

  }

}
