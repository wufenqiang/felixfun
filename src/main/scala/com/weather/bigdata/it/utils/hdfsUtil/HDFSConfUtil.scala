package com.weather.bigdata.it.utils.hdfsUtil

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.log4j.Logger

object HDFSConfUtil {
  private val log:Logger=Logger.getRootLogger
  def isLocal(filepath:Path): Boolean ={
    val uri=filepath.toUri
    val flag = this.isLocal(uri)
    //    val urigetScheme:String=uri.getScheme
    //    val flag:Boolean={
    //      if(urigetScheme==null || urigetScheme.equals("file")){
    //        true
    //      }else{
    //        false
    //      }
    //    }
    //    val msg="isLocal="+flag+";filename="+filename+";urigetScheme="+urigetScheme
    //    log.info(msg)
    flag
  }

  def isLocal(uri:URI): Boolean ={
    val urigetScheme:String=uri.getScheme
    val flag:Boolean={
      if(urigetScheme==null || urigetScheme.equals("file")){
        true
      }else{
        false
      }
    }
    val msg = "isLocal=" + flag + ";uri.toString=" + uri.toString + ";urigetScheme=" + urigetScheme
    log.info(msg)
    flag
  }

  def getConf(filename:String):Configuration={
    val conf0=new Configuration()
    //    conf0.addResource(filename)
    if(!isLocal(filename)){
      conf0.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem")

      //FileSytem类内部有一个static CACHE，用来保存每种文件系统的实例集合，FileSystem类中可以通过”fs.%s.impl.disable.cache”来指定是否缓存FileSystem实例(其中%s替换为相应的scheme，比如hdfs、local、s3、s3n等)，即一旦创建了相应的FileSystem实例，这个实例将会保存在缓存中，此后每次get都会获取同一个实例。所以设为true以后，就能解决上面的异常。
      conf0.setBoolean("fs.hdfs.impl.disable.cache",true)

    }
    conf0
  }

  def isLocal (filename: String): Boolean = {
    val uri = URI.create(filename)
    val flag = this.isLocal(uri)
    //    val urigetScheme:String=uri.getScheme
    //    val flag:Boolean={
    //      if(urigetScheme==null || urigetScheme.equals("file")){
    //        true
    //      }else{
    //        false
    //      }
    //    }

    flag
  }

  //  def getConf(filePath:Path):Configuration={
  //    val conf0=new Configuration()
  //    conf0.addResource(filePath)
  //    if(!isLocal(filePath)){
  //      conf0.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem")
  //
  //      //FileSytem类内部有一个static CACHE，用来保存每种文件系统的实例集合，FileSystem类中可以通过”fs.%s.impl.disable.cache”来指定是否缓存FileSystem实例(其中%s替换为相应的scheme，比如hdfs、local、s3、s3n等)，即一旦创建了相应的FileSystem实例，这个实例将会保存在缓存中，此后每次get都会获取同一个实例。所以设为true以后，就能解决上面的异常。
  //      conf0.setBoolean("fs.hdfs.impl.disable.cache",true)
  //
  //    }
  //    conf0
  //  }
}
