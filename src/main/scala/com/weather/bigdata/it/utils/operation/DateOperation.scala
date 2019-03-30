package com.weather.bigdata.it.utils.operation

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import com.weather.bigdata.it.utils.formatUtil.DateFormatUtil

import scala.collection.mutable.ArrayBuffer

class DateOperation {
  def plusHour(TheDate:Date,n_hour:Int): Date ={
    val cl=Calendar.getInstance
    cl.setTime(TheDate)
    cl.add(Calendar.HOUR,n_hour)
    cl.getTime
  }

  def plusDay(TheDate: Date, n_day: Int): Date = {
    val cl :Calendar= Calendar.getInstance
    cl.setTime(TheDate)
    cl.add(Calendar.DATE, n_day)
    cl.getTime
  }

  def dHours(beforeDate:Date,afterDate:Date): Int ={
    val beforetime=beforeDate.getTime
    val aftertime=afterDate.getTime
    val dtime:Int=((aftertime-beforetime)/1000/3600).toInt
    dtime
  }

  def dateArray(beginDate:Date,endDate:Date,timeStep_Hour:Double): Array[Date] ={
    val AB:ArrayBuffer[Date]=new ArrayBuffer[Date]
    var date=beginDate
    do{
      AB.+=(date)
      date=this.plusHour(date,timeStep_Hour.toInt)
    }while(date.before(endDate) || date.equals(endDate))

    AB.toArray
  }

  def equal(date0:Date,date1:Date): Boolean ={
    val before:Boolean=date0.before(date1)
    val after:Boolean=date0.after(date1)
    if(!before && !after){
      true
    }else{
      false
    }
  }
  private val sdf0:SimpleDateFormat=new SimpleDateFormat("HH:mm")
  def getFormDate0(hourMinStr:String): Date ={
    val hour=sdf0.parse(hourMinStr).getHours
    val min=sdf0.parse(hourMinStr).getMinutes
    val date:Date=new Date
    date.setHours(hour)
    date.setMinutes(min)
    date.setSeconds(0)
    date
  }

  private val sdf1:SimpleDateFormat=new SimpleDateFormat("HH")
  def getFormDate1(hourStr:String): Date ={
    val hour=sdf1.parse(hourStr).getHours
    val date:Date=new Date
    date.setHours(hour)
    date.setMinutes(0)
    date.setSeconds(0)
    date
  }

  private val sdf2:SimpleDateFormat=new SimpleDateFormat("MM月dd日HH时")
  def getFormDate2(MonthDayHourStr:String): Date ={
    val year=new Date().getYear
    val theDate:Date=sdf2.parse(MonthDayHourStr)
    theDate.setYear(year)
    theDate
  }
}

object DateOperation {
  private val dateOp = new DateOperation
  def main(args:Array[String]): Unit ={
    val thedate=this.getFormDate1("09")
    println(DateFormatUtil.YYYYMMDDHHMMSSStr0(thedate))
  }
  def getFormDate0(hourMinStr:String):Date=dateOp.getFormDate0(hourMinStr)
  def getFormDate1(hourStr:String): Date =dateOp.getFormDate1(hourStr)
  def getFormDate2(monthdayhourStr:String):Date=dateOp.getFormDate2(monthdayhourStr)
  def plusHour(TheDate:Date,n_hour:Int):Date=dateOp.plusHour(TheDate,n_hour)
  def plusDay(TheDate: Date, n_day: Int): Date =dateOp.plusDay(TheDate,n_day)
  def dHours(beforeDate:Date,afterDate:Date): Int =dateOp.dHours(beforeDate,afterDate)
  def equal(date0:Date,date1:Date): Boolean =dateOp.equal(date0:Date,date1:Date)
}
