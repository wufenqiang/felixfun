package com.weather.bigdata.it.utils.operation

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

private class ArrayOperation {
  val Kn:Int=3
  /*private def ArrayPlusArray(arr0:Array[String],arr1:Array[String]):Array[String]={
    val arr:Array[String]=new Array[String](arr0.length+arr1.length)
    var i :Int=0
    arr0.foreach(a0=>{arr(i) = a0;i += 1})
    arr1.foreach(a1=>{arr(i) = a1;i += 1})
    arr
  }
  private def ArrayPlusArray(arr:Array[String],str:String):Array[String]={
    val arr1=Array(str)
    this.ArrayPlusArray(arr,arr1)
  }
  private def ArrayPlusArray(str:String,arr:Array[String]):Array[String]={
    this.ArrayPlusArray(arr,str)
  }
  private def ArrayPlusArray(str0:String,str1:String):Array[String]={
    Array(str0,str1)
  }*/
  def ArrayEqual(arr0:Array[Double],arr1:Array[Double]): Boolean ={
    if(arr0.length==arr1.length){
      for(i<- 0 to arr0.length-1){
        if(arr0(i)!=arr1(i)){
          return false
        }
      }
      true
    }else{
      false
    }
  }

  def eachMinus(arr0:Array[Long],arr1:Array[Long]): Array[Long] ={
    if(arr0.length==arr1.length){
      val result=new Array[Long](arr0.length)
      for(i<- 0 to arr0.length-1){
        result(i)=arr0(i)-arr1(i)
      }
      result
    }else{
      println("eachMinus接口输入错误,arr0.length!=arr1.length")
      arr0
    }
  }

  def minusHead(input:Array[String]): Array[String] ={
    val result=new Array[String](input.length-1)
    //    val str:String=input.head
    for(i<-1 to input.length-1){
      result(i-1)=input(i)
    }
    //    input.filter(p=>(!p.equals(str)))
    result
  }

  /*def nearIndex_sequence(values:Array[Double],key:Double): Int ={
    val dvalue:Double=no.Kndec((values(1)-values(0)),3)
    val remainder=no.Kndec(key*1000,3)%no.Kndec(dvalue*1000,3)/1000
    var result=((key-values(0))/dvalue).toInt
    if(result<(values.length-1) && remainder>dvalue/2){
      result += 1
    }
    result
  }*/

  def nearIndex_sequence(values:Array[Double],key:Double): Int ={
    val dvalue: Double = NumOperation.Kndec((values(1) - values(0)), this.Kn)
    val remainder = NumOperation.Kndec(key * 1000, 3) % NumOperation.Kndec(dvalue * 1000, 3) / 1000
    val keyplus: Double = key - values(0)
    val keyminusvalue: Double = NumOperation.Kndec(keyplus, this.Kn)
    var result = NumOperation.Kndec((keyminusvalue / dvalue), this.Kn)
    if ((result < (values.length - 1)) && (dvalue / 2 < remainder)) {
      result += 1
    }
    result.toInt
  }
  def nearIndex_sequence (values: Array[Float], key: Float): Int = {
    val dvalue: Double = NumOperation.Kndec((values(1) - values(0)), this.Kn)
    val remainder = NumOperation.Kndec(key * 1000, 3) % NumOperation.Kndec(dvalue * 1000, 3) / 1000
    val keyplus:Double=key - values(0)
    val keyminusvalue: Double = NumOperation.Kndec(keyplus, this.Kn)
    var result = NumOperation.Kndec((keyminusvalue / dvalue), this.Kn)
    if((result<(values.length-1)) && (dvalue/2 < remainder)){
      result += 1
    }
    result.toInt
  }
  def nearIndexs_sequence(values:Array[Double],keys:Array[Double]): Array[Int] ={
    keys.map(key=>this.nearIndex_sequence(values,key))
  }
  def nearIndexs_sequence (values: Array[Float], keys: Array[Float]): Array[Int] = {
    keys.map(key => this.nearIndex_sequence(values, key))
  }


  def ArithmeticArray(Start: Double, End: Double, Step: Double): Array[Double] ={
    val n=((End-Start)/Step).toInt+1
    this.ArithmeticArray(Start,n,Step)
  }
  def ArithmeticArray(Start: Double, End: Double, n: Int): Array[Double] ={
    if(n>=2){
      val Step:Double=(End-Start)/(n-1)
      this.ArithmeticArray(Start,End,Step)
    }else if(n==1){
      if(Start!=End){
        val e:Exception=new Exception("ArithmeticArray使用错误")
        e.printStackTrace()
        null
      }else{
        Array(Start)
      }
    }else{
      val e:Exception=new Exception("ArithmeticArray使用错误")
      e.printStackTrace()
      null
    }
    /*if(n==1){
      if(Start!=End){
        val e:Exception=new Exception("ArithmeticArray使用错误")
        e.printStackTrace()
        null
      }else{
        Array(Start)
      }
    }else{
      val Step:Double=(End-Start)/(n-1)
      this.ArithmeticArray(Start,End,Step)
    }*/
  }
  def ArithmeticArray(Start: Double, n: Int, Step: Double):Array[Double]={
    val arr:Array[Double]=new Array[Double](n)
    for(i <- 0 to (n-1)){
      arr(i)=Start+Step*i
    }
    arr
  }

  def ArithmeticArray(Start: Float, End: Float, n: Int): Array[Float] = {
    if (n >= 2) {
      val Step: Float = (End - Start) / (n - 1)
      this.ArithmeticArray(Start, End, Step)
    } else if (n == 1) {
      if (Start != End) {
        val e: Exception = new Exception("ArithmeticArray使用错误")
        e.printStackTrace()
        null
      } else {
        Array(Start)
      }
    } else {
      val e: Exception = new Exception("ArithmeticArray使用错误")
      e.printStackTrace()
      null
    }
    /*if(n==1){
      if(Start!=End){
        val e:Exception=new Exception("ArithmeticArray使用错误")
        e.printStackTrace()
        null
      }else{
        Array(Start)
      }
    }else{
      val Step:Double=(End-Start)/(n-1)
      this.ArithmeticArray(Start,End,Step)
    }*/
  }

  def ArithmeticArray(Start: Float, End: Float, Step: Float): Array[Float] = {
    val n: Int = ((End - Start) / Step).toInt + 1
    this.ArithmeticArray(Start: Float, n: Int, Step: Float)
  }

  def ArithmeticArray(Start: Float, n: Int, Step: Float): Array[Float] = {
    val arr: Array[Float] = new Array[Float](n)
    for (i <- 0 to (n - 1)) {
      arr(i) = Start + Step * i
    }
    arr
  }

  def addStr(Str:Array[String],s:Array[String]): Array[String] ={
    val arr:Array[String]=new Array[String](Str.length+s.length)
    var n=0
    Str.foreach(
      s0=>{
        arr(n)=s0
        n +=1
      }
    )
    s.foreach(
      s0=>{
        arr(n)=s0
        n +=1
      }
    )
    arr
  }

  def HashMapSearch(values:Array[Double],key:Double): Int =this.HashMapSearch(values,Array(key)).head
  def HashMapSearch(values:Array[Double],keys:Array[Double]): Array[Int] ={
    //    val result:Array[Int]=new Array[Int](keys.length)
    val map:mutable.HashMap[Double,Int]=this.getHashMap(values)
    val result:Array[Int]={
      try{
        keys.map(k=>map.get(k).get)
      }catch{
        case e:Exception=>{
          var vs="Values="
          values.foreach(v=>vs+=(v+","))
          var ks="Keys="
          keys.foreach(k=>ks+=(k+","))
          val e0:Exception=new Exception("HashMapSearch出错;" + vs + ";" + ks + ";" + e)
          throw e0
          null
        }
      }
    }
    result
  }

  def betweenIndex_sequence(values: Array[Float], keys: Array[Float]): Array[Array[Int]] = {
    val result: Array[Array[Int]] = keys.map(key => this.betweenIndex_sequence(values, key))
    result
  }

  def betweenIndex_sequence(values: Array[Float], key: Float): Array[Int] = {
    val result: Array[Int] = {
      if (key >= values.last) {
        val valuesLen = values.length
        Array(valuesLen - 2, valuesLen - 1)
      } else if (key <= values.head) {
        Array(0, 1)
      } else {
        if (values.contains(key)) {
          val index = this.HashMapSearch(values, key)
          Array(index - 1, index)
        } else {
          val values0 = NumOperation.Kndec(values(0), this.Kn)
          val values1 = NumOperation.Kndec(values(1), this.Kn)
          val Index: Int = ((key - values0) / (values1 - values0)).toInt
          Array(Index, Index + 1)
        }
      }
    }
    result
  }

  def HashMapSearch_contain(values:Array[Double],key:Double):Boolean=this.HashMapSearch_contain(values,Array(key)).head
  def HashMapSearch_contain(values:Array[Double],keys:Array[Double]): Array[Boolean] ={
    keys.map(k=>values.contains(k))
  }

  def betweenIndex_sequence(values:Array[Double],keys:Array[Double]): Array[Array[Int]] ={
    val result:Array[Array[Int]]=keys.map(key=>this.betweenIndex_sequence(values,key))
    result
  }
  def betweenIndex_sequence(values:Array[Double],key:Double): Array[Int] ={
    val result:Array[Int]={
      if(key>=values.last){
        val valuesLen=values.length
        Array(valuesLen-2,valuesLen-1)
      }else if(key<=values.head){
        Array(0,1)
      }else{
        if(values.contains(key)){
          val index=this.HashMapSearch(values,key)
          Array(index-1,index)
        }else{
          val values0 = NumOperation.Kndec(values(0), this.Kn)
          val values1 = NumOperation.Kndec(values(1), this.Kn)
          val Index:Int=((key-values0)/(values1-values0)).toInt
          Array(Index,Index+1)
        }
      }
    }
    result
  }

  def HashMapSearch(values: Array[Float], key: Float): Int = this.HashMapSearch(values, Array(key)).head

  def HashMapSearch(values: Array[Float], keys: Array[Float]): Array[Int] = {
    //    val result:Array[Int]=new Array[Int](keys.length)
    val map: mutable.HashMap[Float, Int] = this.getHashMap(values)
    val result: Array[Int] = {
      try {
        keys.map(k => map.get(k).get)
      } catch {
        case e: Exception => {
          var vs = "Values="
          values.foreach(v => vs += (v + ","))
          var ks = "Keys="
          keys.foreach(k => ks += (k + ","))
          val e0: Exception = new Exception("HashMapSearch出错;" + vs + ";" + ks + ";" + e)
          throw e0
          null
        }
      }
    }
    result
  }

  def getHashMap(values: Array[Float]): mutable.HashMap[Float, Int] = {
    val map: mutable.HashMap[Float, Int] = new mutable.HashMap[Float, Int]
    for (i <- 0 to values.length - 1) {
      map.put(NumOperation.Kndec(values(i), this.Kn), i)
    }
    map
  }

  def betweenIndex_sequence1(values: Array[Double], keys: Array[Double]): Array[(Int, Int)] = {
    val result: Array[(Int, Int)] = keys.map(key => this.betweenIndex_sequence1(values, key))
    result
  }

  def betweenIndex_sequence1(values: Array[Double], key: Double): (Int, Int) = {
    val result: (Int, Int) = {
      if (key >= values.last) {
        val valuesLen = values.length
        (valuesLen - 2, valuesLen - 1)
      } else if (key <= values.head) {
        (0, 1)
      } else {
        if (values.contains(key)) {
          val index = this.HashMapSearch(values, key)
          (index - 1, index)
        } else {
          val values0 = NumOperation.Kndec(values(0), this.Kn)
          val values1 = NumOperation.Kndec(values(1), this.Kn)
          val Index: Int = ((key - values0) / (values1 - values0)).toInt
          (Index, Index + 1)
        }
      }
    }
    result
  }

  def betweenIndex_sequence1(values: Array[Float], keys: Array[Float]): Array[(Int, Int)] = {
    val result: Array[(Int, Int)] = keys.map(key => this.betweenIndex_sequence1(values, key))
    result
  }

  def getHashMap(values:Array[Double]): mutable.HashMap[Double,Int] ={
    val map:mutable.HashMap[Double,Int]=new mutable.HashMap[Double,Int]
    for(i<- 0 to values.length-1){
      map.put(NumOperation.Kndec(values(i), this.Kn), i)
    }
    map
  }

  def betweenIndex_sequence1(values: Array[Float], key: Float): (Int, Int) = {
    val result: (Int, Int) = {
      if (key >= values.last) {
        val valuesLen = values.length
        (valuesLen - 2, valuesLen - 1)
      } else if (key <= values.head) {
        (0, 1)
      } else {
        if (values.contains(key)) {
          val index = this.HashMapSearch(values, key)
          (index - 1, index)
        } else {
          val values0 = NumOperation.Kndec(values(0), this.Kn)
          val values1 = NumOperation.Kndec(values(1), this.Kn)
          val Index: Int = ((key - values0) / (values1 - values0)).toInt
          (Index, Index + 1)
        }
      }
    }
    result
  }

  def remainderGroup(arr:Array[String],N:Int):mutable.HashMap[String,Array[String]] ={
    val arrMap:mutable.HashMap[String,Array[String]]=new mutable.HashMap[String,Array[String]]
    for(i<- 0 to N-1){
      val key:String=i+"_"+N
      val arr0:ArrayBuffer[String]=new ArrayBuffer[String]
      for(j<- 0 to arr.length-1){
        val n:Int=j % N
        if(n == i){
          arr0.+=(arr(j))
        }
      }
      arrMap.put(key,arr0.toArray)
    }
    arrMap
  }
  def randomGroup(arr:Array[String],N:Int):mutable.HashMap[String,Array[String]] ={
    val groupMap:mutable.HashMap[String,Int]=this.GroupMap(arr.length,N)
    val list0:List[Int]=RandomOperation.randomNew(arr.length)
    val arr0:ArrayBuffer[String]=new ArrayBuffer[String]()
    list0.foreach(i=>arr0.+=(arr(i)))

    val arrMap:mutable.HashMap[String,Array[String]]=groupMap.map(f=>{
      val key=f._1
      val N=f._2
      val arr1:Array[String]=new Array[String](N)

      for(i<- 0 to N-1){
        arr1(i)=arr0(0)
        arr0.remove(0)
      }
      (key,arr1)
    })

    arrMap
  }

  def randomGroup(arr:Array[(String,Double)],N:Int):mutable.HashMap[String,Array[(String,Double)]] ={
    val groupMap:mutable.HashMap[String,Int]=this.GroupMap(arr.length,N)
    val list0:List[Int]=RandomOperation.randomNew(arr.length)
    val arr0:ArrayBuffer[(String,Double)]=new ArrayBuffer[(String,Double)]()
    list0.foreach(i=>arr0.+=(arr(i)))

    val arrMap:mutable.HashMap[String,Array[(String,Double)]]=groupMap.map(f=>{
      val key=f._1
      val N=f._2
      val arr1:Array[(String,Double)]=new Array[(String,Double)](N)

      for(i<- 0 to N-1){
        arr1(i)=arr0(0)
        arr0.remove(0)
      }
      (key,arr1)
    })

    arrMap
  }

  def GroupMap(arrLen:Int, N:Int): mutable.HashMap[String,Int] ={
    val resultMap:mutable.HashMap[String,Int]=new mutable.HashMap[String,Int]

    //arrLen=a*N+b
    //b个a+1,，（N-b）个a
    val a:Int= arrLen / N
    val b:Int= arrLen % N


    for(i<-0 to (b-1)){
      val key:String=i+"_"+N
      resultMap.put(key,(a+1))
    }
    for(j<-b to (N-1)){
      val key:String=j+"_"+N
      resultMap.put(key,a)
    }

    if(arrLen!=resultMap.reduce((x,y)=>(x._1,x._2+y._2))._2){
      val msg="GroupMap分块出错。"
      println(msg)
    }


    resultMap
  }

  def FourDimsGet023(input:Array[Array[Array[Array[Double]]]],J:Int): Array[Array[Array[Double]]] ={
    val output :Array[Array[Array[Double]]]= Array.ofDim(input.length,input(0)(0).length, input(0)(0)(0).length)
    for(i<-0 to input.length-1){
      for(k<- 0 to input(0)(0).length-1){
        for(l<- 0 to input(0)(0)(0).length-1){
          output(i)(k)(l)=input(i)(J)(k)(l)
        }
      }
    }
    output
  }
  def FourDimsGet023(input:Array[Array[Array[Array[Float]]]],J:Int): Array[Array[Array[Float]]] ={
    val output :Array[Array[Array[Float]]]= Array.ofDim(input.length,input(0)(0).length, input(0)(0)(0).length)
    for(i<-0 to input.length-1){
      for(k<- 0 to input(0)(0).length-1){
        for(l<- 0 to input(0)(0)(0).length-1){
          output(i)(k)(l)=input(i)(J)(k)(l)
        }
      }
    }
    output
  }
  /*def Arr2Set(arr:Array[AnyRef])={
    val set=new mutable.HashSet[AnyRef]()
    arr.foreach(f=>set.add(f))
    set
  }*/

}

object ArrayOperation{
  private val ao:ArrayOperation=new ArrayOperation
  def remainderGroup(arr:Array[String],N:Int):mutable.HashMap[String,Array[String]]=ao.remainderGroup(arr,N)
  def randomGroup(arr:Array[String],N:Int):mutable.HashMap[String,Array[String]]=ao.randomGroup(arr,N)
  def randomGroup(arr:Array[(String,Double)],N:Int):mutable.HashMap[String,Array[(String,Double)]]=ao.randomGroup(arr,N)
  def GroupMap(arrLen:Int, N:Int):mutable.HashMap[String,Int] = ao.GroupMap(arrLen,N)
  def eachMinus(arr0:Array[Long],arr1:Array[Long]): Array[Long] =ao.eachMinus(arr0,arr1)

  def nearIndex_sequence(values:Array[Double],key:Double): Int =ao.nearIndex_sequence(values,key)

  def nearIndex_sequence(values: Array[Float], key: Float): Int = ao.nearIndex_sequence(values, key)

  def nearIndexs_sequence (values: Array[Double], keys: Array[Double]): Array[Int] = ao.nearIndexs_sequence(values, keys)

  def nearIndexs_sequence(values: Array[Float], keys: Array[Float]): Array[Int] = ao.nearIndexs_sequence(values, keys)

  def HashMapSearch(values:Array[Double],key:Double):Int =ao.HashMapSearch(values,key)

  def HashMapSearch(values: Array[Float], key: Float): Int = ao.HashMapSearch(values, key)
  def HashMapSearch(values:Array[Double],keys:Array[Double]): Array[Int] =ao.HashMapSearch(values,keys)

  def HashMapSearch(values: Array[Float], keys: Array[Float]): Array[Int] = ao.HashMapSearch(values, keys)

  def ArithmeticArray(Start: Double, End: Double, Step: Double): Array[Double] =ao.ArithmeticArray(Start, End, Step)
  def ArithmeticArray(Start: Double, End: Double, n: Int): Array[Double] =ao.ArithmeticArray(Start,End,n)
  def ArithmeticArray(Start: Double, n: Int, Step: Double):Array[Double]=ao.ArithmeticArray(Start,n,Step)

  def ArithmeticArray(Start: Float, End: Float, Step: Float): Array[Float] = ao.ArithmeticArray(Start, End, Step)

  def ArithmeticArray(Start: Float, End: Float, n: Int): Array[Float] = ao.ArithmeticArray(Start, End, n)

  def ArithmeticArray(Start: Float, n: Int, Step: Float): Array[Float] = ao.ArithmeticArray(Start, n, Step)

  def minusHead(input:Array[String]): Array[String] =ao.minusHead(input)

  def FourDimsGet023(input:Array[Array[Array[Array[Double]]]],J:Int)=ao.FourDimsGet023(input,J)
  def FourDimsGet023(input:Array[Array[Array[Array[Float]]]],J:Int)=ao.FourDimsGet023(input,J)

  def addStr (Str: Array[String], s: Array[String]): Array[String] = ao.addStr(Str, s)

  def betweenIndex_sequence (values: Array[Double], keys: Array[Double]): Array[Array[Int]] = ao.betweenIndex_sequence(values, keys)

  def betweenIndex_sequence(values: Array[Float], keys: Array[Float]): Array[Array[Int]] = ao.betweenIndex_sequence(values, keys)
  def betweenIndex_sequence (values: Array[Double], key: Double): Array[Int] = ao.betweenIndex_sequence(values, key)

  def betweenIndex_sequence(values: Array[Float], key: Float): Array[Int] = ao.betweenIndex_sequence(values, key)

  def betweenIndex_sequence1(values: Array[Double], keys: Array[Double]): Array[(Int, Int)] = ao.betweenIndex_sequence1(values, keys)

  def betweenIndex_sequence1(values: Array[Float], keys: Array[Float]): Array[(Int, Int)] = ao.betweenIndex_sequence1(values, keys)

  def betweenIndex_sequence1(values: Array[Double], key: Double): (Int, Int) = ao.betweenIndex_sequence1(values, key)

  def betweenIndex_sequence1(values: Array[Float], key: Float): (Int, Int) = ao.betweenIndex_sequence1(values, key)

  def ArrayEqual (arr0: Array[Double], arr1: Array[Double]): Boolean = ao.ArrayEqual(arr0, arr1)

  def main(args:Array[String]): Unit ={

  }
}

