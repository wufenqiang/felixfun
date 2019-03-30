package com.weather.bigdata.it.utils.operation

object StrOperation {
  def remove(args:Array[String],i:Int): Array[String] ={
    val out=new Array[String](args.length-1)
    var k=0
    for(j<- 0 to args.length-1){
      if(j!=i){
        out(k)=args(j)
        k += 1
      }
    }
    out
  }
}
