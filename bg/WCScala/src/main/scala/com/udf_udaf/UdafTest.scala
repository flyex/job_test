package com.udf_udaf

import java.lang

import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructField, StructType}

object UdafTest {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[2]").appName("udaf").getOrCreate()

    val range: Dataset[lang.Long] = spark.range(1,11)

    val geo = new geomean

    //range.show()

//    spark.udf.register("geo",geo)
//
//    range.createTempView("t_range")
//    val result: DataFrame = spark.sql("select geo(id) as result from t_range")

    import spark.implicits._
    val result: DataFrame = range.agg(geo($"id").as("result"))

    result.show()

    spark.stop()
  }

}
class geomean extends UserDefinedAggregateFunction{

  override def inputSchema: StructType = StructType(List(
    StructField("value",DoubleType)
  ))

  override def bufferSchema: StructType = StructType(List(
    StructField("product",DoubleType),
    StructField("counts",LongType)
  ))

  override def dataType: DataType = DoubleType

  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 1.0
    buffer(1) = 0L
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    buffer(0) = buffer.getDouble(0)*input.getDouble(0)
    buffer(1) = buffer.getLong(1) + 1L
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0) = buffer1.getDouble(0) * buffer2.getDouble(0)
    buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
  }

  override def evaluate(buffer: Row): Any = {
    math.pow(buffer.getDouble(0),1.toDouble / buffer.getLong(1))
  }

}