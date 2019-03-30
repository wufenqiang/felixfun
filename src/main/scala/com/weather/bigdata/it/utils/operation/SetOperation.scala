package com.weather.bigdata.it.utils.operation

import java.util
import java.util.Date

import scala.collection.mutable

object SetOperation {
  /*def sortSet(Keys:mutable.HashSet[(String,Double,Date)]): (mutable.HashSet[String],mutable.HashSet[Double],mutable.TreeSet[Date]) ={
    val key0:mutable.HashSet[String]=new mutable.HashSet[String]()
    val key1:mutable.HashSet[Double]=new mutable.HashSet[Double]()
    val key2:mutable.TreeSet[Date]=new mutable.TreeSet[Date]()
    Keys.foreach(ks=>{
      val k0=ks._1
      val k1=ks._2
      val k2=ks._3
      key0.add(k0)
      key1.add(k1)
      key2.add(k2)
    })
    if((key0.size*key1.size*key2.size)!=Keys.size){
      val msg="sortSet,Key Set has some mistakes."
      val e:Exception=new Exception(msg)
      e.printStackTrace()
    }
    (key0,key1,key2)
  }*/

  def Set2scalaSet(set0:util.Set[String]): mutable.HashSet[String] ={
    val outset:mutable.HashSet[String]=new mutable.HashSet[String]()
    val ites=set0.iterator()
    do{
      outset.add(ites.next())
    }while(ites.hasNext)

    outset
  }
  def RamdomString(set0:mutable.HashSet[String]): String ={
    val menber:String=set0.toList.last
    menber
  }
  def RamdomDouble(set0:mutable.HashSet[Double]): Double ={
    val menber:Double=set0.toList.last
    menber
  }
  def RamdomDate(set0:mutable.TreeSet[Date]): Date ={
    val menber:Date=set0.toList.last
    menber
  }
}
