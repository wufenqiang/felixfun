package com.weather.bigdata.it.utils.formatUtil

import java.text.SimpleDateFormat
import java.util.Date

/**
  * 数据格式化工具类
  * created by robin on 2018-1-26
  * edited by wufenqiang on 2018-3-20
  */
class DateFormatUtil {

  private val mod0:SimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss")
  def YYYYMMDDHHMMSSStr0(date:Date):String=this.mod0.format(date)
  def YYYYMMDDHHMMSS0(dateStr:String):Date=this.mod0.parse(dateStr)

  private val mod1:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss")
  def YYYYMMDDHHMMSSStr1(date:Date):String=this.mod1.format(date)
  def YYYYMMDDHHMMSS1(dateStr:String):Date=this.mod1.parse(dateStr)
  def YYYYMMDDHHMMSSStr1(dateTimeStep:Long):String=this.YYYYMMDDHHMMSSStr1(new Date(dateTimeStep))

  private val mod2:SimpleDateFormat = new SimpleDateFormat("yyyyMMddHH")
  def YYYYMMDDHH0(dateStr:String):Date={
    val date=this.mod2.parse(dateStr)
    date
  }
  def YYYYMMDDHHStr0(date:Date):String=this.mod2.format(date)

  private val mod3:SimpleDateFormat = new SimpleDateFormat("yyyyMMdd")
  def YYYYMMDDStr0(date:Date):String=this.mod3.format(date)
  def YYYYMMDDStr0(dateStr:String):String={
    val date=this.YYYYMMDDHH0(dateStr)
    this.YYYYMMDDStr0(date)
  }

  private val mod4:SimpleDateFormat = new SimpleDateFormat("HH")
  def HHStr(date:Date):String={
    this.mod4.format(date)
  }
  def HHStr(dateStr:String):String={
    val date=this.YYYYMMDDHH0(dateStr)
    this.HHStr(date)
  }
  def HH(date:Date): Int ={
    date.getHours
  }

  private val mod5:SimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm")
  def YYYYMMDDHHMM(dateStr:String):Date=this.mod5.parse(dateStr)
  def YYYYMMDDHHMMStr(date:Date):String=this.mod5.format(date)


  private val mod6:SimpleDateFormat= new SimpleDateFormat("yyyy-MM-dd")
  def YYYYMMDDStr1(date:Date):String=this.mod6.format(date)

  private val mod7:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH")
  def YYYYMMDDHH1(dateStr:String):Date=this.mod7.parse(dateStr)
  def YYYYMMDDHHStr1(date:Date):String=this.mod7.format(date)

  private val mod8:SimpleDateFormat = new SimpleDateFormat("mm")
  def mmStr(date:Date): String =this.mod8.format(date)
  def mmInt(date:Date):Int=this.mod8.format(date).toInt

  private val mod9:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0")
  def YYYYMMDDHHMMSSStr2(date:Date):String=this.mod9.format(date)
  def YYYYMMDDHHMMSS2(dateStr:String):Date=this.mod9.parse(dateStr)

  /*private val mod10_1_08:SimpleDateFormat=new SimpleDateFormat("yyyyMMdd09HH")
  private val mod10_12_08:SimpleDateFormat=new SimpleDateFormat("yyyyMMdd09HH")*/

}

object DateFormatUtil {
  private val dfu = new DateFormatUtil

  def YYYYMMDDHHMMSSStr0 (date: Date): String = this.dfu.YYYYMMDDHHMMSSStr0(date)

  def YYYYMMDDHHMMSS0 (dateStr: String): Date = this.dfu.YYYYMMDDHHMMSS0(dateStr)

  def YYYYMMDDHHMMSSStr1 (date: Date): String = this.dfu.YYYYMMDDHHMMSSStr1(date)

  def YYYYMMDDHHMMSS1 (dateStr: String): Date = this.dfu.YYYYMMDDHHMMSS1(dateStr)

  def YYYYMMDDHHMMSSStr1 (dateTimeStep: Long): String = this.dfu.YYYYMMDDHHMMSSStr1(dateTimeStep)

  def YYYYMMDDHH0 (dateStr: String): Date = this.dfu.YYYYMMDDHH0(dateStr)

  def YYYYMMDDHHStr0 (date: Date): String = this.dfu.YYYYMMDDHHStr0(date)

  def YYYYMMDDStr0 (date: Date): String = this.dfu.YYYYMMDDStr0(date)

  def YYYYMMDDStr0 (dateStr: String): String = this.dfu.YYYYMMDDStr0(dateStr)

  def HHStr (date: Date): String = this.dfu.HHStr(date)

  def HHStr (dateStr: String): String = this.dfu.HHStr(dateStr)

  def HH (date: Date): Int = this.dfu.HH(date)

  def YYYYMMDDHHMM (dateStr: String): Date = this.dfu.YYYYMMDDHHMM(dateStr)

  def YYYYMMDDHHMMStr (date: Date): String = this.dfu.YYYYMMDDHHMMStr(date)

  def YYYYMMDDStr1 (date: Date): String = this.dfu.YYYYMMDDStr1(date: Date)

  def YYYYMMDDHH1 (dateStr: String): Date = this.dfu.YYYYMMDDHH1(dateStr: String)

  def YYYYMMDDHHStr1 (date: Date): String = this.dfu.YYYYMMDDHHStr1(date: Date)

  def mmStr (date: Date): String = this.dfu.mmStr(date: Date)

  def mmInt (date: Date): Int = this.dfu.mmInt(date: Date)

  def YYYYMMDDHHMMSSStr2 (date: Date): String = this.dfu.YYYYMMDDHHMMSSStr2(date: Date)

  def YYYYMMDDHHMMSS2 (dateStr: String): Date = this.dfu.YYYYMMDDHHMMSS2(dateStr: String)

  def main(args:Array[String]): Unit ={
    val now:Date=this.YYYYMMDDHHMMSS0("2018070910080000")
    println(this.mmInt(now))
  }
}
