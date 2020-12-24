package com.flyex.test

object SplitTest {
  def main(args: Array[String]): Unit = {
    val arr1: Array[String] = Array("1 2 3","4 5 6")

    val arr2: Array[String] = arr1.flatMap(_.split(" "))

    arr2.foreach(x => println(x.toBuffer))

  }

}
