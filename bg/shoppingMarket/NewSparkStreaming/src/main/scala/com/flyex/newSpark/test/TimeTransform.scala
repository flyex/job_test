package com.flyex.newSpark.test

import java.text.SimpleDateFormat
import java.util.Date

object TimeTransform {

  def main(args: Array[String]): Unit = {

    val time = "1608043195400"

    val date = new Date(time.toLong)

    val dateFormat = new SimpleDateFormat("yyyyMMddhh")

    val str = dateFormat.format(date)

    println(str)

  }

}
