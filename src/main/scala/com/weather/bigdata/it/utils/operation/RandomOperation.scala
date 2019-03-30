package com.weather.bigdata.it.utils.operation

import java.util.Random

object RandomOperation {
  def randomNew(N:Int,n:Int):List[Int]={
    var resultList:List[Int]=Nil
    while(resultList.length<n){
      val randomNum=(new Random).nextInt(N)
      if(!resultList.exists(s=>s==randomNum)){
        resultList=resultList:::List(randomNum)
      }
    }
    resultList
  }
  def randomNew(N:Int): List[Int] ={
    this.randomNew(N,N)
  }

  def main(args:Array[String]): Unit ={
    val list0=this.randomNew(5)
    println()
  }
}
