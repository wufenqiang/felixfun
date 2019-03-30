package com.weather.bigdata.it.utils.operation

import java.io.File

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import com.weather.bigdata.it.utils.ReadWrite

import scala.collection.mutable

class JsonOperation() {
  val rw:ReadWrite=new ReadWrite()
  def JSONArray2JSONObject(myJsonArray:JSONArray): Array[JSONObject] ={
    val myArr:Array[Object]=myJsonArray.toArray()
    val myjObjects: Array[JSONObject]=myArr.map(Obj=>JSON.parseObject(Obj.toString))
    /*val myjObjects = new Array[JSONObject](myJsonArray.size)
    for(i<- 0 to myJsonArray.size()-1){
      myjObjects(i)=myJsonArray.getJSONObject(i)
    }*/
    myjObjects
  }
 /* def JSONArray2JSONObject(file:File): Array[JSONObject] ={
    val myJsonArray:JSONArray= this.readTXT2JSONArray(file)
    val result:Array[JSONObject]=this.JSONArray2JSONObject(JSONArray)
    result
  }*/
  /*def JSONArray2JSONObject(fileName:String): Array[JSONObject] ={
    val file:File=new File(fileName)
    this.JSONArray2JSONObject(file)
  }*/
  def Str2JSONObject(myString:String): JSONObject ={
    JSON.parseObject(myString)
  }
  def readTXT2JSONArray(file:File): JSONArray ={
    val jsonMessage:String=rw.ReadTXT2String(file)
    JSON.parseArray(jsonMessage)
  }
  def createJSONArray(users:Array[JSONObject]): JSONArray ={
    val jsonArray:JSONArray=new JSONArray()
    var i:Int=0
    for(user<- users){
      jsonArray.add(i,user)
      i += 1
    }
    jsonArray
  }
  def createJSONObject(keys:Array[String],values:Array[String]): JSONObject ={
    if(keys.length==values.length){
      val result:JSONObject=new JSONObject(true)
      for(i<- 0 to keys.length-1){
        result.put(keys(i),values(i))
      }
      result
    }else{
      println("The number of keys and values must be equal.")
      null
    }
  }

  def transpositionJSON(myJson:JSONObject): JSONObject ={
    val result:JSONObject=new JSONObject()
    val jsonKey:mutable.HashSet[String]=SetOperation.Set2scalaSet(myJson.keySet())
    for(k<- jsonKey){
      val v=myJson.getString(k)
      result.put(v,k)
    }
    result
  }
}

object JsonOperation{
  private val JsonOp=new JsonOperation
  def transpositionJSON(myJson:JSONObject):JSONObject =this.JsonOp.transpositionJSON(myJson)
  def Str2JSONObject(myStr:String):JSONObject=this.JsonOp.Str2JSONObject(myStr)

  def createJSONObject (keys: Array[String], values: Array[String]): JSONObject = this.JsonOp.createJSONObject(keys: Array[String], values: Array[String])

  def createJSONArray (users: Array[JSONObject]): JSONArray = this.JsonOp.createJSONArray(users: Array[JSONObject])
}
