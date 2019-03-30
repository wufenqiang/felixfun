//package com.weather.bigdata.it.utils.hdfsUtil
//
//import java.io.{FileSystem => _, _}
//import java.util.Date
//
//import com.weather.bigdata.it.utils.WeatherShowtime
//import com.weather.bigdata.it.utils.operation.FileOperation
//import org.apache.hadoop.conf.Configuration
//import org.apache.hadoop.fs.{FileSystem, _}
//import org.apache.hadoop.io.IOUtils
//
//import scala.collection.mutable.ListBuffer
//
///**
//  *判断是否有文件,子集文件夹不算在内
//  * Created by wufenqiang on 2018/4/1
//  */
//object HDFSOperation {
////  val hdfsmasterName=PropertiesUtil.hdfsmasterName
////  private val hdfsmasterName1=PropertiesUtil.head+PropertiesUtil.masterName
////  val conf :Configuration={
////    val conf0=new Configuration()
////    if(PropertiesUtil.isPrd) {
////      conf0.set("fs.default.name", this.hdfsmasterName)
////      //      conf0.set("fs.default.name", PropertiesUtil.masterName)
////      //FileSytem类内部有一个static CACHE，用来保存每种文件系统的实例集合，FileSystem类中可以通过”fs.%s.impl.disable.cache”来指定是否缓存FileSystem实例(其中%s替换为相应的scheme，比如hdfs、local、s3、s3n等)，即一旦创建了相应的FileSystem实例，这个实例将会保存在缓存中，此后每次get都会获取同一个实例。所以设为true以后，就能解决上面的异常。
////      conf0.setBoolean("fs.hdfs.impl.disable.cache",true)
////    }
////    conf0
////  }
//  //  val hdfs: FileSystem=FileSystem.get(conf)
//  def hdfs: FileSystem =FileSystem.get(new Configuration())
//
//  def reName(fromPathName:String,toPathName:String,cover:Boolean=true): Boolean ={
//    val flag:Boolean={
//      if(this.exists(toPathName)){
//        if(cover){
//          val delete=this.deleteFile_recycle(toPathName,10)
//          if(!delete){
//            val e:Exception=new Exception("删除原始目录出错.")
//            e.printStackTrace()
//          }
//          delete
//        }else{
//          val msg="存在原始目录,reName:cover="+cover
//          val e:Exception=new Exception(msg)
//          e.printStackTrace()
//          true
//        }
//      }else{
//        true
//      }
//    }
//
//    if(flag){
//      val Start=System.currentTimeMillis()
//      val flag0=this.reName(new Path(fromPathName),new Path(toPathName),cover)
//      val End=System.currentTimeMillis()
//      WeatherShowtime.showDateStrOut1("HDFSOperation.reName",Start,End,"Source:"+fromPathName+"=>Target:"+toPathName)
//      flag0
//    }else{
//      val msg="Target="+toPathName+"存在目录没有删除,无法reName"
//      val e:Exception=new Exception(msg)
//      e.printStackTrace()
//      false
//    }
//  }
//  private def reName(fromPath:Path,toPath:Path,cover:Boolean): Boolean ={
//    this.hdfs.rename(fromPath,toPath)
//  }
//  def reNameBak(fromPathName:String,toPathName:String): Boolean ={
//    val toPathName0={
//      if(this.exists(toPathName)){
//        toPathName+"_bak"
//      }else{
//        toPathName
//      }
//    }
//    this.reNameBak(fromPathName,toPathName0)
//  }
//  /*def reNameBak(fromPath:Path,toPath:Path): Boolean ={
//    this.reNameBak(fromPath.toString,toPath.toString)
//  }*/
//
//  /* def getFile(hdfsfilename:String,tmppath:String="/tmp/"):File={
//     val mod:SimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss")
//     val now=new Date()
//     val tmpfile=tmppath+"/"+mod.format(now)+".txt"
//     createFile(tmpfile)
//     copyFileToLocal(hdfsfilename,tmpfile)
//     val path=new Path(hdfsfilename)
//     val parentpath=path.getParent
//     val filename=path.getName
//     val tmpfilename=filename.replace(parentpath.toString,tmppath)
//     val file=new File(tmpfilename)
//     file
//   }*/
//
//  def isDir(pathname:String):Boolean={
//    this.isDir(new Path(pathname))
//  }
//  def isDir(path : Path): Boolean ={
//    this.hdfs.isDirectory(path)
//  }
//
//  def isFile(filename:String):Boolean={
//    this.isFile(new Path(filename))
//  }
//  def isFile(filepath : Path) : Boolean = {
//    this.hdfs.isFile(filepath)
//  }
//
//  def createFile(filename:String): Boolean ={
//    this.createFile(new Path(filename))
//  }
//  def createFile(filepath:Path):Boolean={
//    this.hdfs.createNewFile(filepath)
//  }
//  def createFolder(pathname : String): Boolean ={
//    this.createFolder(new Path(pathname))
//  }
//  def createFolder(path:Path): Boolean ={
//    if(!this.hdfs.exists(path)){
//      this.hdfs.mkdirs(path)
//    }else{
//      false
//    }
//  }
//  /*  def create(filepath:Path):Boolean ={
//      this.create(filepath.getName)
//    }
//    def create(filename:String):Boolean={
//      if(filename.endsWith(".")){
//        this.createFile(filename)
//      }else{
//        this.createFolder(filename)
//      }
//    }*/
//
//  def exists(pathname:String):Boolean={
//    this.exists(new Path(pathname))
//  }
//  def exists(path:Path):Boolean={
//    val exist=this.hdfs.exists(path)
//    exist
//  }
//
//  def filesizeB(path:Path): Long ={
//    this.hdfs.getFileStatus(path).getLen
//  }
//  def filesizeB(pathname:String):Long={
//    this.filesizeB(new Path(pathname))
//  }
//  def filesizeKB(path:Path): Long ={
//    this.filesizeB(path)/1024
//  }
//  def filesizeKB(pathname:String):Long={
//    this.filesizeKB(new Path(pathname))
//  }
//  def filesizeMB(path:Path): Long ={
//    this.filesizeB(path)/(1024*1024)
//  }
//  def filesizeMB(pathname:String):Long={
//    this.filesizeMB(new Path(pathname))
//  }
//
//  def ModificationTime(path:Path): Long ={
//    this.hdfs.getFileStatus(path).getModificationTime
//  }
//  def ModificationTime(pathname:String):Long={
//    this.ModificationTime(new Path(pathname))
//  }
//
//  class MyPathFilter extends PathFilter {
//    override def accept(path: Path): Boolean = true
//  }
//
//  def createLocalFile(filename : String) : File = {
//    val target : File = new File(filename)
//    if(!target.exists){
//      val index = filename.lastIndexOf(File.separator)
//      val parentFullName = filename.substring(0, index)
//      val parent : File = new File(parentFullName)
//
//      if(!parent.exists)
//        parent.mkdirs
//      else if(!parent.isDirectory)
//        parent.mkdir
//
//      target.createNewFile
//    }
//    target
//  }
//
//  def deleteFile(pathname: String) : Boolean = {
//    this.deleteFile(new Path(pathname))
//  }
//  def deleteFile(path:Path):Boolean={
//    if (isDir(path))
//      this.hdfs.delete(path, true)//true: delete files recursively
//    else
//      this.hdfs.delete(path, false)
//  }
//  def deleteFile_recycle(path:Path, recycletime:Int): Boolean ={
//    var delete=this.deleteFile(path)
//    var t:Int = 0
//    while(!delete && t < recycletime){
//      t += 1
//      delete=this.deleteFile(path)
//    }
//    delete
//  }
//  def deleteFile_recycle(pathname:String, recycletime:Int): Boolean ={
//    this.deleteFile_recycle(new Path(pathname),recycletime)
//  }
//
//  def listChildrenLastMonidied(fullName : String): Date ={
//    val listFiles:ListBuffer[String]=this.listChildrenAbsoluteFile(fullName)
//    val listMonidied:ListBuffer[Date]=listFiles.map(file=>new Date(this.ModificationTime(file)))
//    val lastOne:Date= listMonidied.min
//    lastOne
//  }
//  def onlyChildrenLastMonidied(fullName : String): Date ={
//    val listFiles:ListBuffer[String]=this.onlyChildrenAbsoluteFile(fullName)
//    val listMonidied:ListBuffer[Date]=listFiles.map(file=>new Date(this.ModificationTime(file)))
//    val lastOne:Date= listMonidied.min
//    lastOne
//  }
//
//  def listChildrenFile(fullName : String, holder:ListBuffer[String] = new ListBuffer[String]) : ListBuffer[String] = {
//    /*val filesStatus = this.hdfs.listStatus(new Path(fullName), new MyPathFilter)
//    for(status <- filesStatus){
//      val filePath : Path = status.getPath
//      if(isFile(filePath))
//        holder += filePath.getName
//      else
//        listChildrenFile(filePath.toString,holder)
//    }
//    holder*/
//
//    this.listChildrenAbsoluteFile(fullName,holder).map(file=>{new Path(file).getName})
//  }
//  def listChildrenFolder(fullName:String,holder:ListBuffer[String] = new ListBuffer[String]):ListBuffer[String]={
//    val filesStatus = this.hdfs.listStatus(new Path(fullName), new MyPathFilter)
//    for(status <- filesStatus){
//      val filePath : Path = status.getPath
//      if(!isFile(filePath)){
//        holder += filePath.toString
//        listChildrenFolder(filePath.toString,holder)
//      }
//    }
//    holder
//  }
//  def listChildrenAbsoluteFile(fullName : String, holder:ListBuffer[String] = new ListBuffer[String]): ListBuffer[String] ={
//    val filesStatus = this.hdfs.listStatus(new Path(fullName), new MyPathFilter)
//    for(status <- filesStatus){
//      val filePath : Path = status.getPath
//      if(isFile(filePath))
//        holder += filePath.toString
//      else
//        listChildrenAbsoluteFile(filePath.toString,holder)
//    }
//    holder
//  }
//
//  def onlyChildrenAbsoluteFile(fullName : String): ListBuffer[String] ={
//    val holder:ListBuffer[String]=new ListBuffer[String]
//    val filesStatus = this.hdfs.listStatus(new Path(fullName), new MyPathFilter)
//    for(status <- filesStatus){
//      val filePath : Path = status.getPath
//      if(isFile(filePath))
//        holder += filePath.toString
//    }
//    holder
//  }
//  def onlyChildrenFile(fullName : String): ListBuffer[String] ={
//    this.onlyChildrenAbsoluteFile(fullName).map(fileName=>new Path(fileName).getName)
//  }
//
//  /**
//    *判断是否有文件,子集文件夹不算在内
//    * Created by wufenqiang on 2018/4/1
//    */
//  def hasChildrenFile(pathName:String):Boolean={
//    val exist=this.exists(pathName)
//    if(exist){
//      val children=this.listChildrenFile(pathName)
//      if(children.size==0){
//        false
//      }else{
//        true
//      }
//    }else{
//      println(pathName+"文件不存在")
//      false
//    }
//  }
//  def hasChildrenFile(path:Path):Boolean={
//    this.hasChildrenFile(path.getName)
//  }
//
//  /*def copyFile(source: String, target: String): Unit = {
//    val sourcePath = new Path(source)
//    val targetPath = new Path(target)
//    if(!exists(targetPath))
//      this.createFile(targetPath)
//    val inputStream : FSDataInputStream = this.hdfs.open(sourcePath)
//    val outputStream : FSDataOutputStream = this.hdfs.create(targetPath)
//    transport(inputStream, outputStream)
//  }*/
//
//  def copyFolder(sourceFolder: String, targetFolder: String): Unit = {
//    val holder : ListBuffer[String] = new ListBuffer[String]
//    val children : List[String] = listChildrenFile(sourceFolder, holder).toList
//    for(child <- children)
//      copyFileFromLocal(child, child.replaceFirst(sourceFolder, targetFolder))
//  }
//
//  /**
//    * @author wufenqiang
//    * @param SourceStr_fileName
//    * @param TargetStr_pathName
//    * @param deleteSource
//    */
//  /*private def copyFileFromLocal(SourceStr_fileName: String, TargetStr_pathName: String, cover:Boolean=false, deleteSource:Boolean=false): Boolean = {
//    val SourceFile=new File(SourceStr_fileName)
//    val SourcePath=new Path(SourceStr_fileName)
//    val TargetPath=new Path(TargetStr_pathName)
//    val SourcePathStr_path=SourcePath.getParent.toString
//    val TargetStr_fileName={
//      var targetStr_fileName=SourceStr_fileName.replace(SourcePathStr_path,TargetStr_pathName)
//      if(targetStr_fileName.startsWith("//")){
//        targetStr_fileName=targetStr_fileName.replace("//","/")
//      }
//      targetStr_fileName
//    }
//    val TargetPathFS = FileSystem.get(TargetPath.toUri, conf)
//
//    if(!this.exists(TargetStr_pathName)){
//      val creatfloder=this.createFolder(TargetStr_pathName)
//      if(!creatfloder){
//        val msg="远程目录:"+TargetStr_pathName+",无法创建"
//        val e:Exception=new Exception(msg)
//        e.printStackTrace()
//      }
//    }
//    if(this.exists(TargetStr_fileName)){
//      if(PropertiesUtil.overwrite){
//        val delete=HDFSOperation.deleteFile_recycle(TargetStr_fileName,10)
//        //        val delete=HDFSOperation.deleteFile(TargetStr_fileName)
//        if(!delete){
//          val msg=TargetStr_fileName+"远程数据被进程占据无法覆盖,请检查流程"
//          val e:Exception=new Exception(msg)
//          e.printStackTrace()
//        }else{
//          println("copyFileFromLocal覆盖远程数据,path="+TargetStr_fileName)
//        }
//      }
//    }
//    val copyStart=System.currentTimeMillis()
//    //    val copy=FileUtil.copy(SourceFile, TargetPathFS, TargetPath, deleteSource, TargetPathFS.getConf)
//    val copy=FileUtil.copy(SourceFile, TargetPathFS, TargetPath, deleteSource, TargetPathFS.getConf)
//    val copyEnd=System.currentTimeMillis()
//    val msg0="(SourceFile:"+SourceStr_fileName+";TargetStr_pathName:"+TargetStr_pathName+");"+ws.showDateStr("FileUtil.copy:",copyStart,copyEnd)
//    if(copy){
//      println("copyFileFromLocal上传成功"+msg0)
//    }else{
//      val e:Exception=new Exception("copyFileFromLocal上传失败"+msg0)
//      e.printStackTrace()
//      //println(msg0+"copyFileFromLocal复制失败")
//    }
//    copy
//
//  }*/
//
//  def copyFileFromLocal(SourceStr_fileName: String, TargetStr_pathName: String, cover:Boolean=false, deleteSource:Boolean=false): Boolean = {
//    val TargetStr_pathName0:String=TargetStr_pathName.replace(this.hdfsmasterName,"")
//    val SourceStr_path:Path=new Path(SourceStr_fileName)
//    val TargetStr_path:Path=new Path(TargetStr_pathName0)
//    val TargetStr_file:Path={
//      if(TargetStr_pathName0.endsWith("/")){
//        new Path(TargetStr_pathName0+SourceStr_path.getName)
//      }else{
//        new Path(TargetStr_pathName0+"/"+SourceStr_path.getName)
//      }
//    }
//
//    try{
//      val copyStart=System.currentTimeMillis()
//      this.hdfs.copyFromLocalFile(deleteSource,cover,SourceStr_path,TargetStr_file)
//      val copyEnd=System.currentTimeMillis()
//      WeatherShowtime.showDateStrOut1("HDFSOperation.copyFileFromLocal",copyStart,copyEnd,"[Source:"+SourceStr_fileName+"=>Target:"+TargetStr_pathName+"]")
//      true
//    }catch{
//      case e:Exception=>{
//        val e0: Exception = new Exception("hdfs.copyFromLocalFile出错:" + e)
//        e0.printStackTrace()
//        false
//      }
//    }
//  }
//
//  def copyFileToLocal(SourceStr_fileName: String, TargetStr_pathName: String,cover:Boolean=true): Boolean ={
//    //    println("......copyFileToLocal:"+SourceStr_fileName+" -> "+ TargetStr_pathName)
//    val TargetStr_pathName0:String={
//      if(PropertiesUtil.isPrd){
//        val target0=TargetStr_pathName.replace(this.hdfsmasterName1,"")
//        val target={
//          if(target0.endsWith("/")){
//            target0
//          }else{
//            target0+"/"
//          }
//        }
//        target0
//      }else{
//        TargetStr_pathName.replace("file:/","")
//      }
//    }
//    //    this.createFolder(TargetStr_pathName0)
//    println("......copyFileToLocal:"+SourceStr_fileName+" -> "+ TargetStr_pathName0+" ;hdfsmasterName="+this.hdfsmasterName1)
//
//    val SourceStr_path:Path=new Path(SourceStr_fileName)
//    val TargetStr_path:Path=new Path(TargetStr_pathName0)
//    val TargetStr_fileName:String={
//      if(TargetStr_pathName0.endsWith("/")){
//        TargetStr_pathName0+SourceStr_path.getName
//      }else{
//        TargetStr_pathName0+"/"+SourceStr_path.getName
//      }
//    }
//    val TargetStr_file:Path=new Path(TargetStr_fileName)
//    val targetfile=new File(TargetStr_fileName)
//    val flag:Boolean={
//      if(cover){
//        if(targetfile.exists()){
//          println("覆盖本地文件:"+targetfile.getAbsolutePath)
//          targetfile.delete()
//        }
//        this.copyFileToLocal(SourceStr_fileName, TargetStr_pathName,false)
//      }else{
//        if(targetfile.exists()){
//          val e:Exception=new Exception("本地文件存在,无法覆盖")
//          e.printStackTrace()
//          false
//        }else{
//          val in:InputStream=this.hdfs.open(SourceStr_path)
//          val out:OutputStream=new FileOutputStream(TargetStr_fileName)
//          try{
//            IOUtils.copyBytes(in, out, conf)
//            true
//          }catch {
//            case e:Exception=>{
//              e.printStackTrace()
//              false
//            }
//          }finally {
//            in.close()
//            out.close()
//          }
//
//        }
//      }
//    }
//    if(flag && !cover){
//      println("copyFileToLocal:"+SourceStr_fileName+" --> "+TargetStr_pathName)
//    }
//    flag
//  }
//  /*有错
//    private def copyFileToLocal(SourceStr_fileName: String, TargetStr_pathName: String, cover:Boolean=false, deleteSource:Boolean=false): Boolean = {
//    val TargetStr_pathName0=TargetStr_pathName.replace(this.hdfsmasterName,"")
//    val SourceStr_path=new Path(SourceStr_fileName)
//    val TargetStr_path=new Path(TargetStr_pathName0)
//    val TargetStr_file={
//      if(TargetStr_pathName0.endsWith("/")){
//        new Path(TargetStr_pathName0+SourceStr_path.getName)
//      }else{
//        new Path(TargetStr_pathName0+"/"+SourceStr_path.getName)
//      }
//    }
//
//    try{
//      val copyStart=System.currentTimeMillis()
//      this.hdfs.copyToLocalFile(deleteSource,SourceStr_path,TargetStr_file)
//      val copyEnd=System.currentTimeMillis()
//      /*      val msg0="[Source:"+SourceStr_fileName+"=>Target:"+TargetStr_pathName+"];"+ws.showDateStr("copyFromLocalFile:",copyStart,copyEnd)
//            println("HDFSOperation.copyFileToLocal:"+msg0)*/
//      WeatherShowtime.showDateStrOut1("copyFromLocalFile:",copyStart,copyEnd,"Source:"+SourceStr_fileName+"=>Target:"+TargetStr_pathName)
//      true
//    }catch{
//      case e:Exception=>{
//        val e0:Exception=new Exception("hdfs.copyFromLocalFile出错:"+e)
//        e0.printStackTrace()
//        false
//      }
//    }
//    /*val localFile : File = createLocalFile(localTarget)
//
//    val inputStream : FSDataInputStream = this.hdfs.open(new Path(hdfsSource))
//    val outputStream : FileOutputStream = new FileOutputStream(localFile)
//    transport(inputStream, outputStream)*/
//  }*/
//
//  private def copyFolderFromLocal(localSource: String, hdfsTarget: String): Unit = {
//    val localFolder : File = new File(localSource)
//    val allChildren : Array[File] = localFolder.listFiles
//    for(child <- allChildren){
//      val fullName = child.getAbsolutePath
//      val nameExcludeSource : String = fullName.substring(localSource.length)
//      val targetFileFullName : String = hdfsTarget + Path.SEPARATOR + nameExcludeSource
//      if(child.isFile)
//        copyFileFromLocal(fullName, targetFileFullName)
//      else
//        copyFolderFromLocal(fullName, targetFileFullName)
//    }
//  }
//
//  /**
//    *
//    * @param hdfsSource hdfs path
//    * @param localTarget local path
//    */
//  def copyFolderToLocal(hdfsSource: String, localTarget: String): Unit = {
//
//    val hdfsSourcePath=new Path(hdfsSource)
//
//    val localParent:String=localTarget+hdfsSourcePath.getName
//
//    val localTargetPath:String=new Path(localTarget).getParent.toString
//
//    val holder : ListBuffer[String] = new ListBuffer[String]
//    val sourceChildren : List[String] = this.listChildrenAbsoluteFile(hdfsSource).toList
//    val SourceTarget0:List[(String,String)]=sourceChildren.map(s=>{
//      val t:String={
//        val ss=s.split(hdfsSourcePath.getParent.toString.replace("\\","/"))
//        (localTarget.replace("\\","/")+ss.last).replace("//","/")
//      }
//      (s,t)
//    })
//    val SourceTarget1=SourceTarget0.sortBy(st=>{
//      st._2
//    })
//    SourceTarget1.foreach(st=>{
//      val s=st._1
//      val t:String=new File(st._2).getParent.replace("\\","/")
//      if(PropertiesUtil.isPrd){
//        val flag=FileOperation.pathCreat(t)
//        if(!flag){
//          val e: Exception = new Exception("creatPath:" + flag + "文件夹无法生成;t=" + t + ";ip=" + workersUtil.getIp( ))
//          e.printStackTrace()
//        }
//      }else{
//        this.createFolder(t)
//      }
//
//      println(s+" --> "+t)
//      this.copyFileToLocal(s,t,true)
//    })
//
//    /*val hdfsSourceFullName = this.hdfs.getFileStatus(new Path(hdfsSource)).getPath.toString
//    val index = hdfsSourceFullName.length
//    for(child <- sourceChildren){
//      val nameExcludeSource : String = child.substring(index + 1)
//      val targetFileFullName : String = localTarget + File.separator + nameExcludeSource
//      this.copyFileToLocal(child, targetFileFullName)
//    }*/
//  }
//
//  /**
//    * 未测试
//    * @param dir
//    * @return
//    */
//  def subdirs(dir: File): Iterator[File] = {
//    val children = dir.listFiles.filter(_.isDirectory)
//    children.toIterator ++ children.toIterator.flatMap(subdirs _)
//  }
//  def subdirs2(dir: File): Iterator[File] = {
//    val d = dir.listFiles.filter(_.isDirectory)
//    val f = dir.listFiles.filter(_.isFile).toIterator
//    f ++ d.toIterator.flatMap(subdirs2 _)
//  }
//  def subdirs3(dir: File): Iterator[File] = {
//    val d = dir.listFiles.filter(_.isDirectory)
//    val f = dir.listFiles.toIterator
//    f ++ d.toIterator.flatMap(subdirs3 _)
//  }
//  private def transport(inputStream : InputStream, outputStream : OutputStream): Unit ={
//    val buffer = new Array[Byte](64 * 1000)
//    var len = inputStream.read(buffer)
//    while (len != -1) {
//      outputStream.write(buffer, 0, len - 1)
//      len = inputStream.read(buffer)
//    }
//    outputStream.flush()
//    inputStream.close()
//    outputStream.close()
//  }
//
//
//
//  def main(args:Array[String]): Unit ={
//    //
//    //    val hdfsfile=args(0)
//    //    val localfile=args(1)
//    //
//    //    //    this.copyFolderToLocal(hdfsfile,localfile)
//    //    //    this.copyFileToLocal(hdfsfsoile,localfile)
//    //
//    //    val sc=ContextUtil.getSparkContext()
//    //    val rdd=sc.makeRDD(Array(1,2,3,4,5,6,7,8,9,10),10)
//    //    rdd.foreachPartition(f=>{
//    ////      this.copyFolderToLocal(hdfsfile,localfile)
//    //      this.copyFileToLocal(hdfsfile,localfile)
//    //    })
//  }
//}
