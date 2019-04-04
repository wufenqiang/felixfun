package com.weather.bigdata.it.utils.hdfsUtil

import java.io.{FileSystem => _, _}
import java.net.URI

import com.weather.bigdata.it.utils.PropertiesUtil
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs._

import scala.collection.mutable.ArrayBuffer


object HDFSReadWriteUtil {

  def writeTXT(fileName:String, conent:String,append:Boolean):Boolean=this.writeTXT1(fileName, conent,append)
  def writeTXT(fileName:String, conents:Array[String],append:Boolean):Boolean=this.writeTXT1(fileName,conents,append)

  //  private def writeHDFSfile0(fileName:String, conent:String,append:Boolean): Boolean ={
  //    this.writeHDFSfile0(fileName,Array(conent),append)
  //  }
  //  private def writeHDFSfile0(fileName:String, conents:Array[String],append:Boolean): Boolean ={
  //
  //    val now :Date=new Date
  //    val fileNametmp=HDFSFileUtil.path2tmp(fileName,now)
  //
  //    val(tmpfileName:String,filepathName:String)=HDFSFileUtil.exchangePath(fileNametmp,tmpPath)
  //    if(FileOperation.LocalExist(tmpfileName)){
  //      FileOperation.LocalDelete(tmpfileName)
  //    }
  //    FileOperation.fileCreat(tmpfileName)
  //    rw.WriteTXT1(tmpfileName,conents,append)
  //
  //    if(!FileOperation.LocalFileEmpty(tmpfileName) && FileOperation.LocalExist(tmpfileName)){
  //      val copy=HDFSOperation.copyFileFromLocal(tmpfileName,filepathName,true,true)
  //      if(copy){
  //        val reName=HDFSOperation.reName(fileNametmp,fileName,true)
  //        if(reName){
  //          println(ShowUtil.showTimeStampStr1()+":更新文件,地址:"+fileName)
  //          true
  //        }else{
  //          val e:Exception=new Exception("reName出错,没有更新")
  //          e.printStackTrace()
  //          false
  //        }
  //      }else{
  //        val e:Exception=new Exception("没有覆盖文件,写出文件地址:"+ fileName)
  //        e.printStackTrace()
  //        false
  //      }
  //    }else{
  //      val e:Exception=new Exception("本地文件为空/不存在,不做上传覆盖:"+tmpfileName)
  //      e.printStackTrace()
  //      false
  //    }
  //  }

  private def writeTXT1(fileName:String, contents:Array[String],append:Boolean): Boolean ={
    if(HDFSConfUtil.isLocal(fileName)){
      val file=new File(fileName)
      val fw:FileWriter=new FileWriter(file,append)
      val pw:PrintWriter=new PrintWriter(fw)
      val flag0={
        try{
          contents.foreach(content=>pw.println(content))
          true
        }catch{
          case e:Exception=>{
            PropertiesUtil.log.error(e)
            false
          }
        }finally {
          pw.flush()
          fw.flush()
          pw.close()
          fw.close()
        }
      }
//      if(flag0){
//        val fileNamecrc=file.getParent+"/."+file.getName+".crc"
//        HDFSOperation1.delete(fileNamecrc)
//      }

      flag0
    }else{
      val buffersize:Int=4096


      val uri=URI.create(fileName)
      val path=new Path(uri)
      val conf:Configuration=HDFSConfUtil.getConf(fileName)
      //    conf.setBoolean("dfs.support.append", true)

      val fs:FileSystem={
        FileSystem.get(uri,conf)
        //      path.getFileSystem(conf)
      }
      val os:FSDataOutputStream={
        PropertiesUtil.log.info("write in "+fileName)
        if(append && fs.exists(path)){
          //单机此处报错
          fs.append(path,buffersize)
          //        fs.create(path,new Progressable {
          //          override def progress (): Unit = {
          //            fs.append(path,buffersize)
          //          }
          //        })
          //                fs.append(path,buffersize,new Progressable {
          //                  override def progress (): Unit = {
          //                    fs.create(path)
          //                  }
          //                })
        }else{
          fs.create(path)
        }
        //      fs.create(path)
      }


      try{
        contents.foreach(line=>{
          os.write(line.getBytes("UTF-8"))
          os.write("\n".getBytes("UTF-8"))
          os.flush()
        })



        true
      }catch{
        case e:Exception=>{
          e.printStackTrace()
          false
        }
      }finally {
        os.close()
        fs.close()
      }
    }
  }
  private def writeTXT1(fileName:String, content:String,append:Boolean):Boolean=this.writeTXT1(fileName,Array(content),append)

  def readTXT(filename: String): Array[String] = {
    if(HDFSFile.fileempty(filename)){
      Array.empty[String]
    }else{
      val uri=URI.create(filename)
      val path=new Path(uri)
      val conf:Configuration=HDFSConfUtil.getConf(filename)
      val fs={
        if(HDFSConfUtil.isLocal(uri)){
          FileSystem.getLocal(conf)
        }else{
          FileSystem.get(uri,conf)
        }
      }
      val is:FSDataInputStream= fs.open(path)

      val isReader:InputStreamReader=new InputStreamReader(is)
      val bufferedReader:BufferedReader= new BufferedReader(isReader)
      val lines:ArrayBuffer[String]=new ArrayBuffer[String]()
      var line:String=null
      while ({
        line = bufferedReader.readLine
        line != null
      }){
        lines += line
      }

      isReader.close
      bufferedReader.close
      is.close
      fs.close


      lines.toArray
    }
  }

  /*private def addHDFSfile(fileName:String, conents:Array[String]): Unit ={
    val out = {
      if(!HDFSOperation.exists(fileName)){
        HDFSOperation.createFile(fileName)
      }
      new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)))
    }
    val conentBefore=this.readHDFSByTXT(fileName)

    conents.foreach(content=>{
      out.write(content)
      out.write("\n")
    })


    out.close()
  }
  private def addHDFSfile(fileName:String, conents:Array[String],append:Boolean): Unit ={
    this.addHDFSfile(fileName,Array(conent))
  }*/


  def main(args:Array[String]): Unit ={
    val fileName="/D:/tmp/20190329.dat"
    val content="f1b6be3dc7f8c87146b857317a322e9e,file:/D:/tmp/Stream/Obs/20190329/1553703693_201903280000.txt"

    HDFSReadWriteUtil.writeTXT(fileName,content,true)
  }
}
