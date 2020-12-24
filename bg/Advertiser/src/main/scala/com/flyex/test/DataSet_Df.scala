package com.flyex.test

import com.flyex.bean.{User, people}
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.storage.StorageLevel

object DataSet_Df {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName(s"${this.getClass.getSimpleName}").master("local[2]").getOrCreate()

    import sparkSession.implicits._
    val info: Dataset[(Int, String)] = Seq((1,"bob"),(2,"john"),(3,"alice")).toDS()

    info.show()

    info.sort("_2").show()

    val bob = new User("bob",12,"male","登山")
    val bobSet: Dataset[User] = Seq(bob).toDS()
    bobSet.select("habit").show()


    val peopleInfo: Dataset[people] = Seq(people("xiaoli",12),people("alice",18)).toDS()
    val withAge = peopleInfo.where("age = 18")

//    withAge.persist(StorageLevel.MEMORY_AND_DISK)

    withAge.checkpoint()

    sparkSession.close()

  }

}
