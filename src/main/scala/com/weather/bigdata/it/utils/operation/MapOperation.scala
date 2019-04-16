package com.weather.bigdata.it.utils.operation

import com.weather.bigdata.it.utils.PropertiesUtil

import scala.collection.mutable

object MapOperation {
  private def plusHashMap(hashmap0:mutable.HashMap[String,String], hashmap1:mutable.HashMap[String,String]):mutable.HashMap[String,String]={
    val keys=hashmap0.keySet
    keys.foreach(key=>hashmap1.put(key,hashmap0(key)))
    hashmap1
  }
  def showMap(map:Map[String,String]): Unit ={
    map.foreach(f=>(
      println(f._1+"->"+f._2)
      ))
  }
  def MapTranspose(hashmap:mutable.HashMap[String,String]): mutable.HashMap[String,String] ={
    val keySet=hashmap.keySet
    val hashmap0:mutable.HashMap[String,String]=new mutable.HashMap[String,String]()
    keySet.foreach(key=>{
      val value:String=hashmap.get(key).get
      hashmap0.put(value,key)
    })
    if(hashmap0.isEmpty){
      val msg="检查hashmap转换后为空"
      //      println(msg)
      PropertiesUtil.log.info(msg)
    }
    hashmap0
  }

  def MapCounter(map:mutable.HashMap[String,Int]=new mutable.HashMap[String,Int], key:String): mutable.HashMap[String,Int] ={
    if(map.contains(key)){
      val value:Int=map.get(key).get
      map.put(key,value+1)
    }else{
      map.put(key,1)
    }
    map
  }

}
