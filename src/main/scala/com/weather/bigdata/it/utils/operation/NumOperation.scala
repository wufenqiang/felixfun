package com.weather.bigdata.it.utils.operation

class NumOperation {
  def Kndec(input:Double ,n:Int): Double ={
    Math.rint(input*Math.pow(10, n))/Math.pow(10, n)
  }
  def Kndec(inputs:Array[Double],n:Int):Array[Double]={
    inputs.map(input=>this.Kndec(input,n))
  }

  def Kndec (inputs: Array[Float], n: Int): Array[Float] = {
    inputs.map(input => this.Kndec(input, n))
  }

  def Kndec (input: Float, n: Int): Float = {
    this.Kndec(input.toDouble, n).toFloat
  }

  /*  def doubleFormat2_myfun(data: Double):Double={
      this.NumOp.K2dec(data)
    }
    def doubleFormat3_myfun(data: Double):Double={
      this.NumOp.K3dec(data)
    }*/

  /*  def doubleFormat2_myfun(arrData:Array[Double]):Array[Double]={
      val output:Array[Double]=new Array[Double](arrData.length)
      for(i<-0 to arrData.length-1){
        output(i)=this.doubleFormat2_myfun(arrData(i))
      }
      output
    }
    def doubleFormat3_myfun(arrData:Array[Double]):Array[Double]={
      val output:Array[Double]=new Array[Double](arrData.length)
      for(i<-0 to arrData.length-1){
        output(i)=this.doubleFormat3_myfun(arrData(i))
      }
      output
    }*/
  def K2dec (arrData: Array[Double]): Array[Double] = {
    val output: Array[Double] = new Array[Double](arrData.length)
    for (i <- 0 to arrData.length - 1) {
      output(i) = this.K2dec(arrData(i))
    }
    output
  }

  def K2dec(arrData: Array[Float]): Array[Float] = {
    val output: Array[Float] = new Array[Float](arrData.length)
    for (i <- 0 to arrData.length - 1) {
      output(i) = this.K2dec(arrData(i))
    }
    output
  }

  def K2dec (data: Double): Double = {
    f"$data%1.2f".toDouble
    /*val bg :BigDecimal= new BigDecimal(data)

    NumOp.Kndec(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()*100,1)/100*/
  }

  def K2dec(data: Float): Float = {
    f"$data%1.2f".toFloat
    /*val bg :BigDecimal= new BigDecimal(data)

    NumOp.Kndec(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()*100,1)/100*/
  }

  def K3dec (arrData: Array[Double]): Array[Double] = {
    val output: Array[Double] = new Array[Double](arrData.length)
    for (i <- 0 to arrData.length - 1) {
      output(i) = this.K3dec(arrData(i))
    }
    output
  }

  def K3dec(arrData: Array[Float]): Array[Float] = {
    val output: Array[Float] = new Array[Float](arrData.length)
    for (i <- 0 to arrData.length - 1) {
      output(i) = this.K3dec(arrData(i))
    }
    output
  }

  def K3dec (data: Double): Double = {
    f"$data%1.3f".toDouble
    /*val bg :BigDecimal= new BigDecimal(data)

    NumOp.Kndec(bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()*100,1)/100*/
  }

  def K3dec(data: Float): Float = {
    f"$data%1.3f".toFloat
    /*val bg :BigDecimal= new BigDecimal(data)

    NumOp.Kndec(bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()*100,1)/100*/
  }

  def Kdec (arrData: Array[Double]): Array[Double] = {
    val output: Array[Double] = new Array[Double](arrData.length)
    for (i <- 0 to arrData.length - 1) {
      output(i) = this.Kdec(arrData(i))
    }
    output
  }

  def Kdec (data: Double): Double = {
    //    this.doubleFormat2(data)
    this.Kndec(data, 2)
  }

  def Kdec (arrData: Array[Float]): Array[Float] = {
    val output: Array[Float] = new Array[Float](arrData.length)
    for (i <- 0 to arrData.length - 1) {
      output(i) = this.Kdec(arrData(i))
    }
    output
  }

  def Kdec (data: Float): Float = {
    this.Kndec(data, 2)
  }
}

object NumOperation {
  private val no = new NumOperation

  def Kndec (input: Double, n: Int): Double = this.no.Kndec(input, n)

  def Kndec (inputs: Array[Double], n: Int): Array[Double] = this.no.Kndec(inputs, n)

  def Kndec (input: Float, n: Int): Float = this.no.Kndec(input, n)

  def Kndec (inputs: Array[Float], n: Int): Array[Float] = this.no.Kndec(inputs, n)

  def K2dec (data: Double): Double = this.no.K2dec(data)

  def K2dec(data: Float): Float = this.no.K2dec(data)

  def K3dec (data: Double): Double = this.no.K3dec(data)

  def K3dec(data: Float): Float = this.no.K3dec(data)

  def Kdec (data: Double): Double = this.no.Kdec(data)

  def K2dec (arrData: Array[Double]): Array[Double] = this.no.K2dec(arrData)

  def K2dec(arrData: Array[Float]): Array[Float] = this.no.K2dec(arrData)

  def K3dec (arrData: Array[Double]): Array[Double] = this.no.K3dec(arrData)

  def K3dec(arrData: Array[Float]): Array[Float] = this.no.K3dec(arrData)

  def Kdec (arrData: Array[Double]): Array[Double] = this.no.Kdec(arrData)

  def Kdec (arrData: Array[Float]): Array[Float] = this.no.Kdec(arrData)
}
