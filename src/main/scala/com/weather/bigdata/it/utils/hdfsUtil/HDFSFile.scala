package com.weather.bigdata.it.utils.hdfsUtil

import java.io._
import java.net.URI

import com.weather.bigdata.it.utils.PropertiesUtil
import org.apache.commons.codec.digest.DigestUtils
import org.apache.hadoop.fs.{FSDataInputStream, FSDataOutputStream, FileSystem, Path}
import org.apache.hadoop.io.IOUtils

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
      val uri = URI.create(filename)
      val path = new Path(uri)
      val conf = HDFSConfUtil.getConf(filename)
      val fs = FileSystem.get(uri, conf)

      val filename1: String = path.toString

      val filename2 = {
        if (filename1.startsWith("file://")) {
          filename1.split("file://").last
        }else if(filename1.startsWith("file:/")){
          filename1.split("file:/").last
        } else {
          filename1
        }
      }

      val file1=new File("/"+filename2)


      val filewriter: FileWriter = new FileWriter(file1, append)
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

  def fileWriter(is:InputStream,filename0: String, overwrite: Boolean): Boolean ={
    val buffersize:Int=4096

    val filename = HDFSConfUtil.formatFilename(filename0)
    val uri = URI.create(filename)
    val path = new Path(uri)
    val conf = HDFSConfUtil.getConf(filename)
    val fs = FileSystem.get(uri, conf)
    val os: FSDataOutputStream = {
      try {
        fs.create(path, overwrite, buffersize)
      } catch {
        case e: Exception => {
          val e0 = new Exception("overwrite=" + overwrite + ";" + e)
          throw e0
        }
      }
    }

    try {
      IOUtils.copyBytes(is, os, buffersize, true)
      true
    } catch {
      case e: Exception => {
        PropertiesUtil.log.error(e)
        false
      }
    } finally {
      is.close
      os.close
      fs.close
    }
  }

  def main(args:Array[String]): Unit ={
    val filename="/ser/logs/platform/sparksubmit/logs/sparksubmit_Detail/20190503/1556856664410.txt"
    println(this.filterWriter(filename,true))
  }
}
