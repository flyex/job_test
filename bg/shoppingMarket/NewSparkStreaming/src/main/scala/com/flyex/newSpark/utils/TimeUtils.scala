package com.flyex.newSpark.utils

import java.text.SimpleDateFormat
import java.util.Date

object TimeUtils {

  def time2String(time: String): String = {
    val date = new Date(time.toLong)

    val dateFormat = new SimpleDateFormat("yyyyMMddhh")

    val str = dateFormat.format(date)

    str
  }

}
