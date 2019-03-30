//package com.weather.bigdata.it.utils.operation
//
//import java.io._
//import java.nio.channels.FileChannel
//
//object FileOperation {
//  def pathCreat(pathfile:File): Boolean ={
//    if(!pathfile.exists){
//      //println("文件夹创建成功:"+pathfile.getAbsolutePath)
//      pathfile.mkdirs()
//    }else{
//      //println("文件夹创建失败,文件夹已存在:"+pathfile.getAbsolutePath)
//      false
//    }
//  }
//  def pathCreat(pathfileName:String): Boolean ={
//    val pathfile:File=new File(pathfileName)
//    this.pathCreat(pathfile)
//  }
//  def fileCreat(file:File): Boolean ={
//    file.createNewFile()
//  }
//  def fileCreat(fileName:String): Boolean ={
//    val file:File=new File(fileName)
//    this.fileCreat(file)
//  }
//  def LocalExist(pathfile:File): Boolean ={
//    pathfile.exists()
//  }
//  def LocalExist(pathfileName:String): Boolean ={
//    val pathfile:File=new File(pathfileName)
//    this.LocalExist(pathfile)
//  }
//  def LocalDelete(file:File): Boolean ={
//    val flag=file.delete()
//    if(!flag){
//      val e:Exception=new Exception("无法删除file="+file.getAbsoluteFile)
//    }
//    flag
//  }
//  def LocalDelete(fileString:String): Boolean ={
//    val file:File=new File(fileString)
//    this.LocalDelete(file)
//  }
//  def LocalFileEmpty(fileString:String): Boolean ={
//    val file:File=new File(fileString)
//    this.LocalFileEmpty(file)
//  }
//  def LocalFileEmpty(file:File): Boolean ={
//    file.length()==0
//  }
//  def filesizeB(file:File): Long ={
//    file.length()
//  }
//  def filesizeB(fileName:String): Long ={
//    this.filesizeB(new File(fileName))
//  }
//
//  def filesizeMB (fileName: String): Long = {
//    this.filesizeMB(new File(fileName))
//  }
//  def filesizeMB (file: File): Long = {
//    this.filesizeB(file) / (1024 * 1024)
//  }
//
//  def filesizeKB (fileName: String): Long = {
//    this.filesizeKB(new File(fileName))
//  }
//
//  def filesizeKB (file: File): Long = {
//    this.filesizeB(file) / 1024
//  }
//
//  def copyFile0(file:File,toFile:File): Boolean ={
//    val fileName = file.getAbsolutePath
//    val tofileName = toFile.getAbsolutePath
//    if (file.exists) {
//      val bytereadN = 1444
//
//      //读入原文件
//      val inStream = new FileInputStream(fileName)
//      val fs = new FileOutputStream(tofileName)
//      try{
//        val buffer = new Array[Byte](bytereadN)
//        var byteread = inStream.read(buffer)
//        while (byteread != -1) {
//          fs.write(buffer, 0, byteread)
//          byteread = inStream.read(buffer)
//        }
//        true
//      }catch{
//        case e:Exception=>{
//          e.printStackTrace()
//          false
//        }
//      }finally {
//        inStream.close
//        fs.close
//      }
//    }else{
//      false
//    }
//  }
//  def copyFile0(fileName:String,tofileName:String): Boolean={
//    this.copyFile0(new File(fileName),new File(tofileName))
//  }
//  def copyFile1(file:File,toFile:File): Boolean ={
//    val b = new Array[Byte](1024)
//
//    var fis:FileInputStream=null
//    var fos:FileOutputStream=null
//
//    if (file.isDirectory) {
//      var filepath :String= file.getAbsolutePath()
//      filepath=filepath.replaceAll("\\\\", "/")
//      var toFilepath :String= toFile.getAbsolutePath()
//      toFilepath=toFilepath.replaceAll("\\\\", "/")
//      val lastIndexOf = filepath.lastIndexOf("/")
//      toFilepath = toFilepath + filepath.substring(lastIndexOf ,filepath.length())
//      val copy:File=new File(toFilepath)
//      //复制文件夹
//      if (!copy.exists()) {
//        copy.mkdir()
//      }
//      file.listFiles.map(f=>this.copyFile1(f, copy)).reduce((x,y)=>(x && y))
//    }else{
//      if(toFile.isDirectory){
//        var filepath :String= file.getAbsolutePath();
//        filepath=filepath.replaceAll("\\\\", "/");
//        var toFilepath :String= toFile.getAbsolutePath();
//        toFilepath=toFilepath.replaceAll("\\\\", "/");
//        val lastIndexOf :Int= filepath.lastIndexOf("/");
//        toFilepath = toFilepath + filepath.substring(lastIndexOf ,filepath.length());
//        val newFile :File= new File(toFilepath)
//        try {
//          fis = new FileInputStream(file)
//          fos = new FileOutputStream(newFile)
//          var a = fis.read(b)
//          while (a  != -1) {
//            fos.write(b, 0, a)
//            a = fis.read(b)
//          }
//          true
//        } catch {
//          case e: IOException =>{
//            e.printStackTrace
//            false
//          }
//        }finally {
//          fis.close()
//          fos.close()
//        }
//      }else{
//        try {
//          fis = new FileInputStream(file)
//          fos = new FileOutputStream(toFile)
//          var a = fis.read(b)
//          while (a!= -1){
//            fos.write(b, 0, a)
//            a = fis.read(b)
//          }
//          true
//        } catch {
//          case e: Exception =>
//            e.printStackTrace
//            false
//        }finally {
//          fis.close()
//          fos.close()
//        }
//      }
//    }
//  }
//  def copyFile1(fileName:String,tofileName:String): Boolean={
//    this.copyFile1(new File(fileName),new File(tofileName))
//  }
//  def copyFile2(file:File,toFile:File): Boolean ={
//    var fi :FileInputStream = null
//    var fo :FileOutputStream = null
//    var in :FileChannel= null
//    var out :FileChannel= null
//    try{
//      fi = new FileInputStream(file)
//      fo = new FileOutputStream(toFile)
//      in = fi.getChannel //得到对应的文件通道
//      out = fo.getChannel
//      in.transferTo(0, in.size, out) //连接两个通道，并且从in通道读取，然后写入out通道
//      true
//    }catch {
//      case e:IOException=>{
//        e.printStackTrace
//        false
//      }
//    }finally {
//      import java.io.IOException
//      try {
//        fi.close
//        in.close
//        fo.close
//        out.close
//      } catch {
//        case e: IOException =>
//          e.printStackTrace( )
//      }
//    }
//  }
//  def copyFile2(fileName:String,tofileName:String): Boolean={
//    this.copyFile2(new File(fileName),new File(tofileName))
//  }
//  def copyFile3(file:File,toFile:File): Boolean ={
//    var fis :BufferedInputStream= null
//    var fos :BufferedOutputStream= null
//    try {
//      fis = new BufferedInputStream(new FileInputStream(file))
//      fos = new BufferedOutputStream(new FileOutputStream(toFile))
//      val buf = new Array[Byte](2048)
//      var i :Int= fis.read(buf)
//      while ( i != -1) {
//        fos.write(buf, 0, i)
//        i = fis.read(buf)
//      }
//      true
//    } catch {
//      case e: Exception =>{
//        e.printStackTrace
//        false
//      }
//    } finally{
//      try {
//        fis.close
//        fos.close
//      } catch {
//        case e: IOException =>
//          e.printStackTrace
//      }
//    }
//  }
//  def copyFile3(fileName:String,tofileName:String): Boolean={
//    this.copyFile3(new File(fileName),new File(tofileName))
//  }
//  def copyFile4(file:File,toFile:File): Boolean ={
//    val input = new FileInputStream(file)
//    val output = new FileOutputStream(toFile)
//    try {
//
//      val buf:Array[Byte] = new Array[Byte](1024)
//      var bytesRead:Int = input.read(buf)
//      while (bytesRead != 0) {
//        output.write(buf, 0, bytesRead)
//        bytesRead = input.read(buf)
//      }
//      true
//    } catch {
//      case e: IOException =>{
//        false
//      }
//    }finally{
//      input.close
//      output.close
//    }
//  }
//  def copyFile4(fileName:String,tofileName:String): Boolean={
//    this.copyFile4(new File(fileName),new File(tofileName))
//  }
//  def copyFile5(file:File,toFile:File): Boolean ={
//    var inputChannel: FileChannel = null
//    var outputChannel: FileChannel = null
//    try {
//      inputChannel= new FileInputStream(file).getChannel
//      outputChannel= new FileOutputStream(toFile).getChannel
//      outputChannel.transferFrom(inputChannel, 0, inputChannel.size)
//      true
//    } catch {
//      case e: IOException => {
//        e.printStackTrace
//        false
//      }
//    }finally {
//      inputChannel.close
//      outputChannel.close
//    }
//  }
//  def copyFile5(fileName:String,tofileName:String): Boolean={
//    this.copyFile5(new File(fileName),new File(tofileName))
//  }
//  /*def copyFile6(file:File,toFile:File): Boolean ={
//    try{
//      Files.copy(file.toPath, toFile.toPath)
//      true
//    }catch {
//      case e: IOException =>{
//        e.printStackTrace
//        false
//      }
//    }
//  }
//  def copyFile6(fileName:String,tofileName:String): Boolean={
//    this.copyFile6(new File(fileName),new File(tofileName))
//  }*/
//
////  def getpathFiles(path:File): Array[File] ={
////    if(path.exists()){
////      path.listFiles()
////    }else{
////      val msg=path.getAbsolutePath+"文件夹为空。"
////      val e:Exception=new Exception(msg)
////      e.printStackTrace()
////      throw e
////    }
////  }
//  def main(args:Array[String]): Unit ={
//    val fileName="/D:/tmp/1.txt/"
//    println(this.LocalFileEmpty(fileName))
//  }
//}
