package com.weather.bigdata.it.utils

import java.text.SimpleDateFormat

class WeatherShowtime {
  //显示程序计算时间//显示程序计算时间
  private def showDate(modelName: String, begin: Long, end: Long): Array[String] = {
    val msg = new Array[String](3)
    val showDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    val time1 = showDateFormat.format(begin)
    val time2 = showDateFormat.format(end)
    msg(0) = modelName + " " + "开始时间:" + time1
    msg(1) = modelName + " " + "结束时间:" + time2
    msg(2) = modelName + " " + "运行时间:" + (end - begin) / 1000 + "s"
    msg
  }
  private def showDateStr(modelName: String, begin: Long, end: Long):String={
    val strs=this.showDate(modelName,begin,end)
    strs.reduce((x,y)=>(x+";"+y))
  }
  private def showDateStrOut0(modelName: String, begin: Long, end: Long): Unit ={
    val strs=this.showDateStr(modelName, begin, end)
    println(strs)
  }
  def showDateStrOut1(modelName: String, begin: Long, end: Long,confStr:String): Unit ={
    println(this.showDateStr(modelName,begin,end)+";配置参数:"+confStr)
  }
  def showDateStrOut1(modelName: String, begin: Long, end: Long): Unit ={
    this.showDateStrOut1(modelName, begin, end,"")
  }
  def showDateStrOut1(modelName: String, begin: Long, end: Long,confStr:String,tab:String):Unit={
    tab+this.showDateStrOut0(modelName,begin,end)+";配置参数:"+confStr+";"+tab
  }
//  def showDateStrOut1
  /*private def showDateSystemOut(modelName: String, begin: Long, end: Long): Unit = {
    val msg = showDate(modelName, begin, end)
    for(i<- 0 to msg.length-1){
      System.out.println(msg(i))
    }
  }*/
}

object WeatherShowtime{
  val tab0:String="...................."

  private val ws=new WeatherShowtime
  private def showDate(modelName: String, begin: Long, end: Long): Array[String] ={
    ws.showDate(modelName, begin, end)
  }
  private def showDateStr(modelName: String, begin: Long, end: Long): String ={
    ws.showDateStr(modelName, begin, end)
  }
  private def showDateStrOut0(modelName: String, begin: Long, end: Long): Unit ={
    ws.showDateStrOut0(modelName, begin, end)
  }
  def showDateStrOut1(modelName: String, begin: Long, end: Long,confStr:String): Unit ={
    ws.showDateStrOut1(modelName, begin, end,confStr)
  }
  def showDateStrOut1(modelName: String, begin: Long, end: Long): Unit ={
    ws.showDateStrOut1(modelName, begin, end)
  }
  def showDateStrOut1(modelName: String, begin: Long, end: Long,confStr:String,tab:String): Unit ={
    ws.showDateStrOut1(modelName, begin, end,confStr,tab)
  }
}
