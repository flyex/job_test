package com.ipLocation

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object IpLocationBySql {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[2]").appName("asd").getOrCreate()

    val ipRules: Dataset[String] = spark.read.textFile("")

    import spark.implicits._
    val ipRulesDF: DataFrame = ipRules.map(line => {
      val fields: Array[String] = line.split("[|]")
      val sNum = fields(0)
      val eNum = fields(1)
      val province = fields(2)
      (sNum, eNum, province)
    }).toDF("snum", "enum", "province")

    ipRulesDF.createTempView("t_ipRules")

    val ips: Dataset[String] = spark.read.textFile("")

    val ipDF: DataFrame = ips.map(line => {
      val fields: Array[String] = line.split("[|]")
      val ipNum = fields(1)
      (ipNum)
    }).toDF("ipnum")

    ipDF.createTempView("t_ips")

    //表join
    val result: DataFrame = spark.sql("select province,count(*) as counts " +
      "from" +
      " t_ips join t_ipRules " +
      "on " +
      "(ipnum >= snum and ipnum <= enum) group by province order by counts desc ")

    //DSL_join
    ipDF.join(ipRulesDF,$""===$"","left_outer")



    result.show()

    spark.stop()

  }

}
