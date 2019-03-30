package com.weather.bigdata.it.utils.hdfsUtil

import java.net.URI

import org.apache.commons.codec.digest.DigestUtils
import org.apache.hadoop.fs.{FSDataInputStream, FileSystem, Path}

object HDFSFile {

  def filesizeB(filename:String): Long ={
    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    fs.getFileStatus(path).getLen
  }
  def filesizeKB(filename:String): Long ={
    this.filesizeB(filename)/1024
  }
  def filesizeMB(filename:String): Long ={
    this.filesizeKB(filename)/1024
  }
  def filesizeGB(filename:String): Long ={
    this.filesizeMB(filename)/1024
  }

  def fileempty(filename:String): Boolean ={
    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    fs.getFileStatus(path).getLen == 0
  }

  def fileModificationTime(filename:String):Long={
    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    fs.getFileStatus(path).getModificationTime
  }

  //
  def fileMD5(filename:String): String ={
    val buffersize:Int=4096
//    PropertiesUtil.log.info("fileMD5("+filename+")")
    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    val is: FSDataInputStream = fs.open(path,buffersize)
    val md5=DigestUtils.md5Hex(is)
    is.close()
    fs.close()
    md5
  }

  def main(args:Array[String]): Unit ={
    val filename=args(0)
    println(this.fileMD5(filename))
  }
}
