package com.weather.bigdata.it.utils.formatUtil

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import com.weather.bigdata.it.utils.operation.ArrayOperation


class StringDate {
  def ArithmeticArrayDate_Hour (sdf: SimpleDateFormat, startDate: Date, times: Array[Double]): Array[String] = {
    val cl: Calendar = Calendar.getInstance( )
    //    val times:Array[Int]=Array.range(timeStep,timeStep*len)
    times.map(time => {
      cl.setTime(startDate)
      cl.add(Calendar.HOUR, time.toInt)
      sdf.format(cl.getTime)
    })
  }
}

object StringDate {
  private val sd: StringDate = new StringDate

  def main (args: Array[String]): Unit = {
    val sdf: SimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm")
    val fcdate: Date = DateFormatUtil.YYYYMMDDHHMMSS0("20190220080000")
    val times = ArrayOperation.ArithmeticArray(12.0d, 240.0d, 12.0d)
    val ArrayString: Array[String] = this.ArithmeticArrayDate_Hour(sdf, fcdate, times)
    println("timesLen=" + times.length)
    ArrayString.foreach(f => println(f))
  }

  def ArithmeticArrayDate_Hour (sdf: SimpleDateFormat, startDate: Date, times: Array[Double]): Array[String] = this.sd.ArithmeticArrayDate_Hour(sdf, startDate, times)
}
