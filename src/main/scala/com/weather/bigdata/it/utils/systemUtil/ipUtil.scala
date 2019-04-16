package com.weather.bigdata.it.utils.systemUtil

import java.net.InetAddress

object ipUtil {
  def getIp (): String = {
    InetAddress.getLocalHost.getHostAddress
  }

  def getHost (): String = {
    InetAddress.getLocalHost.getHostName
  }
}
