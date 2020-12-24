package com.join

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object JoinTest2 {

  def main(args: Array[String]): Unit = {


    val spark: SparkSession = SparkSession
      .builder().appName("t2").master("local[2]").getOrCreate()

    import spark.implicits._
    val d1 = spark.createDataset(Seq((1,12),(1,13),(3,14)))
    val d2 = spark.createDataset(Seq((1,"a"),(1,"b"),(2,"c")))

    val f1 = d1.toDF("id","age")
    val f2 = d2.toDF("id","title")

    f1.createTempView("t1")
    f2.createTempView("t2")

    val res: DataFrame = spark.sql("select * from t1 left anti join t2 on t1.id=t2.id")

    res.show()

    spark.stop()

  }

}
