package com.join

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object JoinTest {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[2]").appName("JoinTest").getOrCreate()

    //spark.sql.autoBroadcastJoinThreshold
    //spark.sql.join.preferSortMergeJoin
    spark.conf.set("spark.sql.autoBroadcastJoinThreshold", 1)

    import spark.implicits._
    val person: Dataset[String] = spark.createDataset(List(("1,li,china"), ("2,ki,japan"), ("3,xi,usa"), ("4,bob,uk")))

    val personDF: DataFrame = person.map(p => {
      val fields = p.split(",")
      val id = fields(0)
      val name = fields(1)
      val nation = fields(2)
      (id, name, nation)
    }).toDF("id", "name", "nation")

    val chinese2English: Dataset[String] = spark.createDataset(List(("china,中国"), ("japan,日本"), ("usa,美国")))

    val chinese2EnglishDF: DataFrame = chinese2English.map(c2e => {
      val fields = c2e.split(",")
      val eng = fields(0)
      val ch = fields(1)
      (eng, ch)
    }).toDF("eng", "ch")

    //    personDF.createTempView("t_person")
    //    chinese2EnglishDF.createTempView("t_info")

    //    val result = spark.sql("select name,ch from t_person left outer join t_info on nation=eng")

    //    val result: DataFrame = personDF.join(chinese2EnglishDF,$"nation"===$"eng","left_outer")

    //    val result = personDF.join(chinese2EnglishDF).where($"nation" === $"eng")
    //
    //    result.write.csv("d:\\hahaNoUse")

    //chinese2EnglishDF.repartition()
    personDF.cache().count()

    val result: DataFrame = personDF.join(chinese2EnglishDF,$"nation" === $"eng")

    result.explain()

    result.show()

    val result2: DataFrame = personDF.join(chinese2EnglishDF,$"nation" === $"eng")
    result2.explain()


    spark.stop()

  }

}
