package com.weather.bigdata.it.utils

import org.apache.log4j.{Logger, PropertyConfigurator}

private object PropertiesUtil {
  //  val log:Logger=Logger.getRootLogger

  private var log0: Logger = null

  def log: Logger = {
    if (this.log0 == null) {
      this.log0 = {
        PropertyConfigurator.configure(Thread.currentThread.getContextClassLoader( ).getResource("felixfun_log4j.properties"))
        val log1 = Logger.getLogger("felixfun")
        log1.info("")
        log1
      }
      //      PropertyConfigurator.configure(Thread.currentThread.getContextClassLoader().getResource("felixfun_log4j.properties"))
      //      this.log0=Logger.getRootLogger
    }
    this.log0
  }
}
