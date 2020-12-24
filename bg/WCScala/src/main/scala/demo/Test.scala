package demo

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SQLContext, types}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

object Test {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName(s"${this.getClass.getSimpleName}")

    val sc = new SparkContext(conf)
    val ssc = new SQLContext(sc)

    val numbs1 = Seq(1,2,3,4,5,6,7)
    val numbs2 = Seq(11,12,13,14,15,16,17)
    val res: Seq[Int] = numbs1.zip(numbs2).map(t => {
      t._1 + t._2
    })

    val tuples: Seq[(String, Int)] = Seq((("xiaoli"),(1)),(("xiaoliu"),(1)),(("xiaoliu"),(2)),(("xiaoli"),(2)))
    val value: RDD[(String, Int)] = sc.makeRDD(tuples)
    value.reduceByKey(_+_)
    value.groupByKey().map(t => (t._1,t._2.sum))

    val idAndAge = Seq((1,12),(2,12),(3,14),(4,14),(5,14))
    val iaaR: RDD[(Int, Int)] = sc.makeRDD(idAndAge)
    val result2: RDD[(Int, Int)] = iaaR.map(t => (t._2, t._1)).groupByKey().mapValues(
      itr => itr.toList.length
    )

    val storeAndUser: Seq[(String, Int)] = Seq(("李宁",1),("李宁",3),("李宁",5),("李宁",1),("李宁",2),("李宁",2))
    val su = sc.makeRDD(storeAndUser)
    val uv: RDD[(String, Int)] = su.groupByKey().mapValues(t=> t.toList.distinct.length)

    val stt: RDD[TT] = su.map(user => {
      val brand = user._1
      val id = user._2
      new TT(brand, id)
    })

    def func(words:String)(implicit people:String) = println(words+people)
    println(uv.collect().toBuffer)

    sc.stop()

    //******************************************************************



  }


}
class TT(brand:String,id:Integer)
