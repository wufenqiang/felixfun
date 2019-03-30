package com.weather.bigdata.it.utils

import java.io._

import scala.collection.mutable.ArrayBuffer


class ReadWrite {
  def ReadTXT2String(file:File): String ={
    var br:BufferedReader=null
    var sb:StringBuffer=null
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))//这里可以控制编码
      sb = new StringBuffer()
      var line:String=br.readLine()
      while (line!= null){
        sb.append(line)
        line=br.readLine()
      }
    }catch{
      case e:Exception=>{
        e.printStackTrace()
      }
    }finally {
      br.close()
    }
    sb.toString
  }
  def ReadTXT2String(fileName:String): String = this.ReadTXT2String(new File(fileName))

  def ReadTXT2ArrString(file:File): Array[String] ={
    var br:BufferedReader=null
    var sb:ArrayBuffer[String]=new ArrayBuffer[String]
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))//这里可以控制编码
      var line:String=br.readLine()
      while (line!= null){
        sb.+=(line)
        line=br.readLine()
      }
    }catch{
      case e:Exception=>e.printStackTrace()
    }finally {
      br.close()
    }
    sb.toArray
  }
  def ReadTXT2ArrString(fileName:String):Array[String]=this.ReadTXT2ArrString(new File(fileName))




  def WriteTXT2(fileName:String,contents:ArrayBuffer[Array[String]],split:String):Unit={
    val contents0:ArrayBuffer[String]=contents.map(content=>{
      var str0:String=""
      content.foreach(str=>{
        str0=str0+str+split
      })
      str0
    })
    this.WriteTXT1(fileName,contents0)
  }

  def WriteTXT1(fileName:String,content: String): Unit ={
    this.WriteTXT1(fileName,Array(content))
  }
  def WriteTXT1(fileName:String,contents:Array[String]): Unit ={
    val f:File=new File(fileName)
    this.WriteTXT1(f,contents,true)
  }
  def WriteTXT1(fileName:String,contents:ArrayBuffer[String]):Unit={
    this.WriteTXT1(fileName,contents.toArray)
  }

  def WriteTXT1(file:File,contents:Array[String],append:Boolean): Unit ={
    val fw:FileWriter=new FileWriter(file,append)
    val pw:PrintWriter=new PrintWriter(fw)
    contents.foreach(content=>pw.println(content))
    pw.flush()
    fw.flush()
    pw.close()
    fw.close()
  }
  def WriteTXT1(file:File,content:String,append:Boolean): Unit = this.WriteTXT1(file,Array(content),append)
  def WriteTXT1(fileName:String,contents:Array[String],append:Boolean): Unit =this.WriteTXT1(new File(fileName),contents,append)
  def WriteTXT1(fileName:String,content:String,append:Boolean): Unit = this.WriteTXT1(fileName,Array(content),append)

  def getEnvProperties(): Unit ={
    /* val user= System.getProperty("user.name")
     println("<-------------------------------user="+user+"------------------------------->")*/
    //    System.getProperties.list(System.out)
    //    ShowUtil.PathShowInfo()
  }
}

object ReadWrite{
  private val rw:ReadWrite=new ReadWrite
  def ReadTXT2String(fileName:String)=rw.ReadTXT2String(fileName)
  def ReadTXT2ArrString(file:File):Array[String]=rw.ReadTXT2ArrString(file)
  def ReadTXT2ArrString(fileName:String):Array[String]=rw.ReadTXT2ArrString(fileName)
  def WriteTXT1(fileName:String,contents:Array[String],append:Boolean):Unit=rw.WriteTXT1(fileName,contents,append)
  def WriteTXT1(fileName:String,content:String,append:Boolean):Unit=rw.WriteTXT1(fileName,content,append)
}
