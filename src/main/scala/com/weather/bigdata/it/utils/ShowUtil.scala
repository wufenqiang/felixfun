package com.weather.bigdata.it.utils

import java.util
import java.util.Date

import com.weather.bigdata.it.utils.formatUtil.DateFormatUtil
import com.weather.bigdata.it.utils.operation.SetOperation

//import com.weather.forecast.NMC.utils.Properties.PropertiesUtil
import scala.collection.mutable

object ShowUtil {
  def ArrayShowInfo(arr:Array[String]): Unit ={
    /*for(i<-0 to arr.length-1){
      print("args("+i+")="+arr(i)+";")
    }
    println()*/
    //    println(this.ArrayShowStr(arr))
    PropertiesUtil.log.info(this.ArrayShowStr(arr))
  }
  def ArrayShowStr(arr:Array[String]):String={
    var str=""
    for(i<-0 to arr.length-1){
      str += "args("+i+")="+arr(i)+";"
    }
    str
  }
  def ArrayShowStr(arr:Array[Double]): String ={
    /*var str=""
    for(i<-0 to arr.length-1){
      str += "args("+i+")="+arr(i)+";"
    }
    str*/
    this.ArrayShowStr(arr.map(a => a.toString))
  }

  def ArrayShowStr (arr: Array[Int]): String = {
    this.ArrayShowStr(arr.map(a => a.toString))
  }

  def ArrayShowStr(arrs:Array[(String,String)]):String ={
    /*var str=""
    for(i<- 0 to arrs.length-1){
      str += "args("+i+")_1="+arrs(i)._1+",args("+i+")_2="+arrs(i)._2+";"
    }
    str*/
    this.ArrayShowStr(arrs.map(a => a.toString))
  }

  def ArrayShowStr(arr:Array[(Double,Date,String)]): String ={
    var str=""
    for(i<-0 to arr.length-1){
      str += "args(" + i + ")=" + DateFormatUtil.YYYYMMDDHHMMSSStr1(arr(i)._2) + "[ocfdate];" + arr(i)._1 + "[timeStep]," + arr(i)._3 + "[ocffile];"
    }
    str
  }

  def ArrayShowInfo(arr:Array[Double]): Unit ={
    val arrStr=arr.map(arrs=>arrs.toString)
    this.ArrayShowInfo(arrStr)
  }

  def ArrayShowError(arr:Array[String]):Unit={
    val arrs:String=arr.reduce((x,y)=>(x+";"+y))
    val e :Exception=new Exception(arrs)
    e.printStackTrace()
    /*var es:Array[Exception]=arr.map(a=>new Exception(a))
    this.ArrayShowError(es)*/
  }
  def ArrayShowError(es:Array[Exception]):Unit={
    es.foreach(e=>e.printStackTrace())
  }

  def ArrayStr2Str(arr:Array[String],splitStr:String): String ={
    var outStr=""
    arr.foreach(str=>{
      outStr +=(str+splitStr)
    })
    outStr
  }

  def MapShowStr(map:mutable.HashMap[Double,Int]): String ={
    var outStr=""
    val keySet=map.keySet
    keySet.foreach(k=>outStr+=(k+"->"+map.get(k).get+","))
    outStr
  }
  def MapShowKeys(map:util.HashMap[String, AnyRef]):String={
    var outStr=""
    val keySet:mutable.HashSet[String]=SetOperation.Set2scalaSet(map.keySet())
    keySet.foreach(str=>outStr=outStr+str+",")
    outStr
  }

  //  def PathShowInfo(folderName:String=PropertiesUtil.getWriteTmp()):Unit={
  //    val childrenFile:ListBuffer[String]=HDFSOperation.listChildrenFile(folderName)
  //    childrenFile.foreach(
  //      fileName=>{
  //        /*if(fileName.endsWith(".txt")||fileName.endsWith(".nc")){
  //          val file=folderName+"/"+fileName
  //          HDFSOperation.deleteFile_recycle(file,10)
  //          System.out.println(file+"已经清除")
  //        }*/
  //        System.out.println(fileName)
  //      }
  //    )
  //  }

  def showTimeStampStr0(): String ={
    val now=new Date
    DateFormatUtil.YYYYMMDDHHMMSSStr0(now)
  }
  def showTimeStampStr1():String ={
    val now =new Date
    DateFormatUtil.YYYYMMDDHHMMSSStr1(now)
  }
  def main(args:Array[String]): Unit ={
    //    this.PathShowInfo()
  }
}
