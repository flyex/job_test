package com.flyex.optimizerSQL

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object OptSQL {

  def main(args: Array[String]): Unit = {

    val session = SparkSession.builder().appName("test").master("local[2]").getOrCreate()

    val sc = session.sparkContext

    sc.getConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    sc.getConf.registerKryoClasses(Array(classOf[Score], classOf[Student]))
    session.sqlContext.setConf("spark.sql.parquet.compression.codec", "snappy")

    //join方式设置
    session.conf.set("spark.sql.autoBroadcastJoinThreshold", 100)
    session.conf.set("spark.sql.join.preferSortMergeJoin", false)


    val scoreRdd = sc.makeRDD(Seq((1, 90, 100, 95), (2, 80, 100, 97), (3, 90, 90, 95), (4, 80, 100, 97), (
      5, 80, 100, 97), (6, 80, 100, 97), (7, 80, 100, 97), (8, 80, 100, 97), (9, 80, 100, 97),
      (10, 80, 100, 97), (11, 80, 100, 97), (12, 80, 100, 97), (13, 80, 100, 97), (14, 80, 100, 97),
      (1, 90, 100, 95), (2, 80, 100, 97), (3, 90, 90, 95), (4, 80, 100, 97), (5, 80, 100, 97), (6, 80, 100, 97), (7, 80, 100, 97), (8, 80, 100, 97)))

    val stuRdd = sc.makeRDD(Seq((1, "xiaom", "man"), (2, "xiaozhao", "man"), (3, "alex", "woman"), (4, "john", "man"))).repartition(3)

    //TODO rdd->ds
    val rddScore: RDD[Score] = scoreRdd.map(r => {
      Score(r._1, r._2, r._3, r._4)
    })

    val stuRDD = stuRdd.map(s => {
      Student(s._1, s._2, s._3)
    })

    import session.implicits._
    val scoreDS: Dataset[Score] = rddScore.toDS()
    val scoreDF: DataFrame = rddScore.toDF()
    val stuDS = stuRDD.toDS()
    val stuDF = stuRDD.toDF()

    //TODO 创建df
    //    import session.implicits._
    //    val scoreDF = scoreRdd.toDF("id","chinese","math","english")
    //scoreDF.show()
    //scoreDF.select("id").where("math > 95").show()
    //df操作 注册视图
    //    scoreDF.createTempView("t_score");
    //    val res = session.sql("select * from t_score")
    //    res.show()

    //TODO df -> ds
    //    val scoreDS: Dataset[Score] = scoreDF.as[Score]
    //scoreDS.show()

    //TODO ds->rdd
    //    val scoreRDD = scoreDS.rdd
    //    scoreRDD.foreach(s =>{
    //      print(s.id + " " + s.chinese)
    //    })

    //TODO ds->df
    //    val scoreDDF = scoreDS.toDF()
    //scoreDDF.show()

    //    stuDS.createTempView("student")
    //    session.sql("select count(*) from student group by sex").explain()


    //TODO sparkSql join Test
    //默认启用SortMergeJoin

    //stuDS.cache().count()
    val res: DataFrame = stuDS.join(scoreDS, Seq("id"), "right")

    res.explain()
    //res.coalesce(1).write.csv("D:\\testDataOut\\csv\\")


    sc.stop()
    session.stop()
  }

}

case class Score(id: Int, chinese: Int, math: Int, english: Int)

case class Student(id: Int, name: String, sex: String)