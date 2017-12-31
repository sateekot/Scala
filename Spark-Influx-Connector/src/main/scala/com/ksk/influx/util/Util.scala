package com.ksk.influx.util

import org.apache.spark.sql.types.{DataType, IntegerType, LongType, StringType}
import org.apache.spark.sql.types.DateType

object Util {
  def castTo(value : String, dataType : DataType) = {
    println("dataType = "+dataType+" :: value = "+value)
    dataType match {
      case _ : DateType => value
      case _ : LongType => value.toLong
      case _ : StringType => value
    }
  }
}