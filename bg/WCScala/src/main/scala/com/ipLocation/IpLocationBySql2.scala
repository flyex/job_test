package com.ipLocation

import com.flyex.broadcast.MyUtils
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object IpLocationBySql2 {

  def main(args: Array[String]) = {

    val spark: SparkSession = SparkSession.builder().appName("").master("").getOrCreate()

    val ipRules: Dataset[String] = spark.read.textFile("")

    import spark.implicits._
    val ipRulesRdd: Dataset[(Long, Long, String)] = ipRules.map(line => {
      val fields = line.split("|")
      val sNum = fields(0).toLong
      val eNum = fields(1).toLong
      val province = fields(2)
      (sNum, eNum, province)
    })

    //创建ipRules广播变量
    val ipRules2: Array[(Long, Long, String)] = ipRulesRdd.collect()

    val ipRulesRef: Broadcast[Array[(Long, Long, String)]] = spark.sparkContext.broadcast(ipRules2)

    val ips: Dataset[String] = spark.read.textFile("")

    val ipDF: DataFrame = ips.map(line => {
      val fields: Array[String] = line.split("[|]")
      val ipNum = fields(1).toLong
      (ipNum)
    }).toDF("ipnum")

    ipDF.createTempView("t_ips")


    //自定义函数  udf注册函数 内传函数名和具体实现
    spark.udf.register("ip2Province",(ipNum:Long) =>{

      val ipValue: Array[(Long, Long, String)] = ipRulesRef.value

      val index: Int = MyUtils.binarySearch(ipValue,ipNum)

      var province = "未知"
      if (index != -1){
        province = ipValue(index)._3
      }
      province
    })

    //执行sql sql内使用自定义函数 函数参数为from表的列名 返回值作为新表列值
    val result: DataFrame = spark.sql("select " +
      "ip2Province(ipNum) as province ," +
      "count(*) as counts " +
      "from t_ips " +
      "group by province " +
      "order by counts desc")

    result.show()

    spark.stop()



  }
}
