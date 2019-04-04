package com.weather.bigdata.it.utils.hdfsUtil

import java.net.{InetAddress, URI}

import com.weather.bigdata.it.utils.PropertiesUtil
import org.apache.hadoop.fs._
import org.apache.hadoop.io.IOUtils

import scala.collection.mutable.ListBuffer


object HDFSOperation1 {


  class pathfilter extends MyPathFilterInterface{
    override def accept(path: Path): Boolean = true
  }

  def listChildrenAbsoluteFile(filename : String, holder:ListBuffer[String] = new ListBuffer[String],mypathfilter:MyPathFilterInterface=new pathfilter): ListBuffer[String] ={

    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    val filesStatus = fs.listStatus(path)
    for(status <- filesStatus){
      val filePath : Path = status.getPath
      if(fs.isFile(path))
        holder += filePath.toString
      else
        listChildrenAbsoluteFile(filePath.toString,holder)
    }
    holder.filter(p => {
      mypathfilter.accept(new Path(URI.create(p)))
    })
  }
  def listChildrenAbsoluteFilePath(filename : String, holder:ListBuffer[Path] = new ListBuffer[Path],mypathfilter:MyPathFilterInterface=new pathfilter): ListBuffer[Path] ={

    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    val filesStatus = fs.listStatus(path)
    for(status <- filesStatus){
      val filePath : Path = status.getPath
      if(fs.isFile(path))
        holder += filePath
      else
        listChildrenAbsoluteFilePath(filePath.toString,holder)
    }
    holder.filter(p => {
      mypathfilter.accept(p)
    })
  }

  def isDir(filename:String):Boolean={

    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)

    val exist=fs.exists(path)
    val isDir=fs.isDirectory(path)

    val flag:Boolean={
      exist && isDir
    }
    if(!exist) {
      val msg=filename+";exists="+exist
      PropertiesUtil.log.warn(msg)
    }
    fs.close()
    flag
  }
  def isFile(filename:String):Boolean={

    val uri = URI.create(filename)
    val path = new Path(uri)
    val conf = HDFSConfUtil.getConf(filename)
    val fs = FileSystem.get(uri, conf)

    val exist=fs.exists(path)
    val isFile=fs.isFile(path)

    val flag:Boolean={
      exist && isFile
    }
    if(!exist) {
      val msg=filename+";exists="+exist
      PropertiesUtil.log.warn(msg)
    }
    fs.close()
    flag
  }

  def exists(filename:String): Boolean ={
    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    fs.exists(path)
  }

  def createFile(filename:String): Boolean ={
    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    try{
      fs.create(path)
      true
    }catch {
      case e:Exception=>{
        PropertiesUtil.log.error(e)
        false
      }
    }finally {
      fs.close()
    }
  }
  def mkdirs(filename:String):Boolean={
    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    if(!fs.exists(path)){
      fs.mkdirs(path)
    }else{
      val msg="文件已存在"+filename+";默认mkdir返回true"
      PropertiesUtil.log.warn(msg)
      true
    }
  }

  def delete(filename:String): Boolean ={
    val uri=URI.create(filename)
    val path=new Path(uri)
    val conf=HDFSConfUtil.getConf(filename)
    val fs=FileSystem.get(uri,conf)
    fs.deleteOnExit(path)
  }

  def renamefile (from_filename: String, to_filename: String, cover: Boolean = true): Boolean = {
    if ((HDFSConfUtil.isLocal(from_filename) && HDFSConfUtil.isLocal(to_filename)) || (!HDFSConfUtil.isLocal(from_filename) && !HDFSConfUtil.isLocal(to_filename))) {
      val from_uri = URI.create(from_filename)
      val from_path = new Path(from_uri)

      val to_uri = URI.create(to_filename)
      val to_path = new Path(to_uri)
      val to_conf = HDFSConfUtil.getConf(to_filename)
      val to_fs = FileSystem.get(to_uri, to_conf)

      to_fs.rename(from_path, to_path)
    } else {
      this.copyfile(from_filename, to_filename, false, true)
    }

  }

  def movefile (from_filename: String, to_filename: String, overwrite: Boolean, deleteSource: Boolean): Boolean = {
    val buffersize: Int = 4096

    val from_uri = URI.create(from_filename)
    val from_path = new Path(from_uri)
    val from_conf = HDFSConfUtil.getConf(from_filename)
    val from_fs = FileSystem.get(from_uri, from_conf)

    val to_uri = URI.create(to_filename)
    val to_path = new Path(to_uri)
    val to_conf = HDFSConfUtil.getConf(to_filename)
    val to_fs = FileSystem.get(to_uri, to_conf)

    if (from_fs.getFileStatus(from_path).toString.equals(to_fs.getFileStatus(to_path).toString)) {
      val e: Exception = new Exception("源地址与目标地址相同")
      PropertiesUtil.log.error(e)
      false
    } else {
      val from_filename_islocal = HDFSConfUtil.isLocal(from_filename)
      val to_filename_islocal = HDFSConfUtil.isLocal(to_filename)
      if (from_filename_islocal && to_filename_islocal) {
        this.copyfile(from_filename, to_filename, overwrite, true)
      } else if (!from_filename_islocal && to_filename_islocal) {
        try {
          from_fs.moveToLocalFile(from_path, to_path)
          true
        } catch {
          case e: Exception => {
            false
          }
        }
      } else if (from_filename_islocal && !to_filename_islocal) {
        try {
          to_fs.moveFromLocalFile(from_path, to_path)
          true
        } catch {
          case e: Exception => {
            false
          }
        }
      } else if (!from_filename_islocal && !to_filename_islocal) {
        this.copyfile(from_filename, to_filename, overwrite, true)
      } else {
        val e: Exception = new Exception("未知错误")
        PropertiesUtil.log.error(e)
        false
      }
    }
  }

  def copyfile (from_filename0: String, to_filename0: String, overwrite: Boolean = false, deleteSource: Boolean = false): Boolean = {
    val from_filename = {
      if (HDFSConfUtil.isLocal(from_filename0) && !from_filename0.startsWith("file://")) {
        "file://" + from_filename0
      } else {
        from_filename0
      }
    }
    val to_filename = {
      if (HDFSConfUtil.isLocal(to_filename0) && !to_filename0.startsWith("file://")) {
        "file://" + to_filename0
      } else {
        to_filename0
      }
    }

    PropertiesUtil.log.info("copyfile:from_filename(" + from_filename + ")-->to_filename(" + to_filename + ");overwrite=" + overwrite + ";deleteSource=" + deleteSource)

    val buffersize:Int=4096

    val from_uri=URI.create(from_filename)
    val from_path=new Path(from_uri)
    val from_conf=HDFSConfUtil.getConf(from_filename)
    val from_fs=FileSystem.get(from_uri,from_conf)

    val to_uri=URI.create(to_filename)
    val to_path=new Path(to_uri)
    val to_conf=HDFSConfUtil.getConf(to_filename)
    val to_fs=FileSystem.get(to_uri,to_conf)

    if(from_path.toString.equals(to_path.toString)) {
      val e: Exception = new Exception("源地址("+from_filename+");与目标地址("+to_filename+")相同")
      PropertiesUtil.log.error(e)
      false
    }else if(from_fs.isFile(from_path) && to_fs.isDirectory(to_path) && from_fs.getFileStatus(from_path.getParent).toString.equals(to_fs.getFileStatus(to_path).toString)){
      val e: Exception = new Exception("源地址[file]("+from_filename+"),目标地址[directory]("+to_filename+"),源地址父路径与目标地址相同")
      PropertiesUtil.log.error(e)
      false
      //      try {
      //        val from_in: FSDataInputStream = from_fs.open(from_path)
      //
      //        val from_filename_only=from_path.getName
      //        from_fs.get
      //
      //        val to_uri0=URI.create(to_filename+"/"+from_filename_only)
      //        val to_path0=new Path(to_uri0)
      //
      //        val to_out: FSDataOutputStream = to_fs.create(to_path0, overwrite, buffersize)
      //        IOUtils.copyBytes(from_in, to_out, buffersize, true)
      //        true
      //      } catch {
      //        case e: Exception => {
      //          false
      //        }
      //      }
    }else{

      val flag0:Boolean={
        if (from_fs.exists(from_path)) {
          if (from_fs.isFile(from_path) && !to_fs.isDirectory(to_path)) {
            val from_in: FSDataInputStream = from_fs.open(from_path)
            val to_out: FSDataOutputStream = {
              try {
                to_fs.create(to_path, overwrite, buffersize)
              } catch {
                case e: Exception => {
                  val e0 = new Exception("overwrite=" + overwrite + ";" + e)
                  throw e0
                }
              }
            }
            try {
              IOUtils.copyBytes(from_in, to_out, buffersize, true)
              true
            } catch {
              case e: Exception => {
                PropertiesUtil.log.error(e)
                false
              }
            } finally {
              from_in.close( )
              to_out.close( )
            }
          } else if (from_fs.isFile(from_path) && to_fs.isDirectory(to_path)) {
            val from_in: FSDataInputStream = from_fs.open(from_path)
            val from_filename_only = from_path.getName
            val to_uri0 = URI.create(to_filename + "/" + from_filename_only)
            val to_path0 = new Path(to_uri0)
            val to_out: FSDataOutputStream = {
              try {
                to_fs.create(to_path0, overwrite, buffersize)
              } catch {
                case e: Exception => {
                  val msg = "overwrite=" + overwrite + ";" + e
                  PropertiesUtil.log.warn(msg)
                  return false
                }
              }
            }
            try {
              IOUtils.copyBytes(from_in, to_out, buffersize, true)
              true
            } catch {
              case e: Exception => {
                PropertiesUtil.log.error(e)
                false
              }
            } finally {
              from_in.close( )
              to_out.close( )
            }
          } else {
            val msg="源地址("+from_filename+"),目标地址("+to_filename+"),暂不支持该类复制"
            PropertiesUtil.log.warn(msg)
            false
          }
        } else {
          //*****************************************************
          val from_parent_String = from_path.getParent.toString
          PropertiesUtil.log.info("from_parent_String=" + from_parent_String)
          HDFSOperation1.listChildrenAbsoluteFile(from_parent_String).foreach(f => {
            PropertiesUtil.log.info(f)
          })
          //*****************************************************

          val msg = "源地址(" + from_filename + "),不存在,ip=" + this.getIp( )
          PropertiesUtil.log.error(msg)
          false
        }
      }
      val flag1: Boolean = {
        if (flag0) {
          if (deleteSource) {
            val msg = "deleteSource=" + deleteSource
            PropertiesUtil.log.info(msg)
            from_fs.deleteOnExit(from_path)
          } else {
            true
          }
        } else {
          false
        }
      }
      if (flag1) {
        val msg = "源地址[file](" + from_filename + ")->目标地址[directory](" + to_filename + ")"
        PropertiesUtil.log.info(msg)
      }else{
        val msg = "源地址[file](" + from_filename + ")无法复制到目标地址[directory](" + to_filename + "),ip=" + this.getIp( )
      }
      flag1
    }
  }

  private def getIp (): String = {
    val ip: String = InetAddress.getLocalHost.getHostAddress
    ip
  }

  def main(args:Array[String]): Unit ={
    //    val from_filename:String=args(0)
    //    val to_filename:String=args(1)
    //    val flag=this.copyfile(from_filename,to_filename,true)
    //    println(flag)

    //    val flag0=this.delete(args(0))
    //    println(flag0)

    //    val flag0=this.isDir(args(0))
    //    val flag1=this.isFile(args(0))
    //    println("isDir="+flag0+";isFile="+flag1)
    //    this.isLocal(args(0))
    //    this.isLocal(args(1))

    val path=args(0)
    val childern:ListBuffer[String]=this.listChildrenAbsoluteFile(path)
    childern.foreach(f=>println(f))
  }
}
