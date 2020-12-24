package com.sparkSql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{DoubleType, IntegerType, LongType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SQLContext, types}
import org.apache.spark.{SparkConf, SparkContext}

object SQlDemo2 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("sqldemo")

    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)


    val lines = sc.textFile("hdfs://hdp-01:9000/sparkSqlData/person")

    val rowRdd: RDD[Row] = lines.map(line => {
      val fields = line.split(",")
      val id: Long = fields(0).toLong
      val name: String = fields(1)
      val age: Int = fields(2).toInt
      val fv: Double = fields(3).toDouble
      Row(id, name, age, fv)
    })

    val schema: StructType = StructType(List(
      StructField("id", LongType, true),
      StructField("name", StringType, true),
      StructField("age", IntegerType, true),
      StructField("fv", DoubleType, true)
    ))

    val df: DataFrame = sqlContext.createDataFrame(rowRdd,schema)
//不使用临时表，直接用dataFrame
//    df.registerTempTable("t_person")
//
//    val result: DataFrame = sqlContext.sql("select * from t_person order by fv desc,age asc")

    val df1: DataFrame = df.select("name","age","fv")

    //df1.printSchema()

    import sqlContext.implicits._
    val df2: Dataset[Row] = df1.orderBy($"fv" desc,$"age" asc).filter($"age" >=18)

    df2.show()
    df2.write.json("hdfs://hdp-01:9000/sparkJsonOut")
    sc.stop()


  }

}
