package com.weather.bigdata.it.utils.hdfsUtil

import java.io._
import java.net.URI

import org.apache.commons.codec.digest.DigestUtils
import org.apache.hadoop.fs.{FSDataInputStream, FSDataOutputStream, FileSystem, Path}

object HDFSFile {

  def filesizeB (filename0: String): Long = {
    val filename = HDFSConfUtil.formatFilename(filename0)

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

  def fileempty (filename0: String): Boolean = {
    val filename = HDFSConfUtil.formatFilename(filename0)

    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    fs.getFileStatus(path).getLen == 0
  }

  def fileModificationTime (filename0: String): Long = {
    val filename = HDFSConfUtil.formatFilename(filename0)

    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    fs.getFileStatus(path).getModificationTime
  }

  //
  def fileMD5 (filename0: String): String = {
    val filename = HDFSConfUtil.formatFilename(filename0)

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

  def fileInputStream (filename0: String): InputStream = {
    val filename = HDFSConfUtil.formatFilename(filename0)

    val uri = URI.create(filename)
    val path = new Path(uri)
    val conf = HDFSConfUtil.getConf(filename)
    val fs = FileSystem.get(uri, conf)

    val is: FSDataInputStream = fs.open(path)
    is
  }

  def filterWriter (filename0: String, append: Boolean): Writer = {
    val filename = HDFSConfUtil.formatFilename(filename0)
    if (HDFSConfUtil.isLocal(filename)) {
      val filename1 = {
        if (filename.startsWith("file://")) {
          filename.split("file://").last
        } else {
          filename
        }
      }
      val filewriter: FileWriter = new FileWriter(filename1, append)
      filewriter
    } else {
      val buffersize: Int = 4096

      val uri = URI.create(filename)
      val path = new Path(uri)
      val conf = HDFSConfUtil.getConf(filename)
      val fs = FileSystem.get(uri, conf)

      val os: FSDataOutputStream = {
        if (append && fs.exists(path)) {
          //单机此处报错
          fs.append(path, buffersize)
        } else {
          fs.create(path)
        }
      }

      val filewriter: OutputStreamWriter = new OutputStreamWriter(os)

      filewriter
    }
  }

  def main(args:Array[String]): Unit ={
    val filename=args(0)
    println(this.fileMD5(filename))
  }
}
