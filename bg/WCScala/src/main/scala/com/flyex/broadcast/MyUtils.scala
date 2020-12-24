package com.flyex.broadcast

import java.sql.DriverManager

import org.apache.spark.storage.StorageLevel

import scala.io.{BufferedSource, Source}

object MyUtils {

  def ip2Long(ip:String):Long = {
    val ips = ip.split("[.]")
    var ipNum = 0L
    for (i <-0 until ips.length){
      ipNum = ips(i).toLong | ipNum << 8L
    }
    ipNum
  }



  def readRules(path:String) : Array[(Long,Long,String)] = {
    val bf: BufferedSource = Source.fromFile(path)

    val lines: Iterator[String] = bf.getLines()
    val rules: Array[(Long, Long, String)] = lines.map(line => {
      val fileds: Array[String] = line.split("[|]")
      val startNum = fileds(2).toLong
      val endNum = fileds(3).toLong
      val province = fileds(6)
      (startNum, endNum, province)
    }).toArray
    rules
  }
  StorageLevel.MEMORY_AND_DISK
  def binarySearch(lines:Array[(Long,Long,String)],ip:Long) : Int = {
    var low = 0
    var high = lines.length-1
    while (low<=high){
      val middle: Int = (low+high)/2
      if ((ip>=lines(middle)._1)&&(ip<=lines(middle)._2)){
        return middle
      }
      if (ip<lines(middle)._1){
        high = middle-1
      }else{
        low = middle+1
      }
    }
    -1
  }

  def data2Mysql(it:Iterator[(String,Int)]) : Unit = {
    val conn = DriverManager.getConnection("jdbs:mysql://localhost:3306/bigdata?characterEncoding=utf-8","root","213qwe")

    val pstm = conn.prepareStatement("insert into as values (?,?)")

    it.foreach(tp =>{
      pstm.setString(1,tp._1)
      pstm.setInt(2,tp._2)
      pstm.executeUpdate()
    })

    if (pstm != null){
      pstm.close()
    }
    if (conn != null){
      conn.close()
    }
  }

  def main(args: Array[String]): Unit = {
    val rules: Array[(Long, Long, String)] = readRules("D:\\ip.txt")

    val unit = ip2Long("114.215.43.42")

    val i: Int = binarySearch(rules,unit)

    val province = rules(i)._3

    println(province)
  }
}
