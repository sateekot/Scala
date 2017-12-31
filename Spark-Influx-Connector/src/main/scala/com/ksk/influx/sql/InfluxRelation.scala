package com.ksk.influx.sql

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.sources.BaseRelation
import org.apache.spark.sql.sources.PrunedScan
import org.apache.spark.sql.sources.TableScan
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StructType

import com.ksk.influx.util.Util
import com.paulgoldbaum.influxdbclient.InfluxDB

class InfluxRelation(host: String, port: Int, dbName: String, dbTable: String, override val sqlContext: SQLContext) extends BaseRelation with TableScan 
with PrunedScan
{

  override def schema: StructType = {

    StructType(
      StructField("time", StringType, false) ::
        StructField("host", StringType, true) ::
        StructField("value", StringType, true) :: Nil)

  }
  override def buildScan(): RDD[Row] = {
    val queryString = s"SELECT * FROM $dbTable"
    println(queryString)

    val fields = schema.fields
    val influxdb = InfluxDB.connect(host, port)
    val database = influxdb.selectDatabase(dbName)

    val result = database.query(queryString)
    val newResult = Await.result(result, 5 seconds)


    val data = newResult.series.head.records.map(line => line.allValues.toSeq)
    val tmp = data.map(words => words.zipWithIndex.map {
      case (value, index) =>
        val colName = fields(index).name
        Util.castTo(
          value.toString(),
          fields(index).dataType)
    })
    val test = tmp.map(s => Row.fromSeq(s))
    database.close()
    sqlContext.sparkContext.makeRDD(test)

  }
  
  def buildScan(requiredColumns : Array[String]) : RDD[Row]  = {
    println("requiredColumns::"+requiredColumns.length)
    val columnsAsString : String = {
      val sb = new StringBuilder()
      requiredColumns.foreach(col => {
        if(sb.isEmpty) sb.append(col) else sb.append(",").append(col)
      })
      if(sb.isEmpty) "*" else sb.toString()
    }
    println("Filtered Columns :: "+columnsAsString)
    val queryString = s"SELECT ${columnsAsString} FROM $dbTable"
    println(queryString)

    val fields = schema.fields
    val influxdb = InfluxDB.connect(host, port)
    val database = influxdb.selectDatabase(dbName)

    val futureResult = database.query(queryString)
    
     val newResult = Await.result(futureResult, 5 seconds)
    
    val data = newResult.series.head.records.map(line => line.allValues.toSeq)
    val tmp = data.map(words => words.zipWithIndex.map {
      case (value, index) =>
        val colName = fields(index).name
        Util.castTo(
          value.toString(),
          fields(index).dataType)
    })
    val test = tmp.map(s => Row.fromSeq(s))
    database.close()
    sqlContext.sparkContext.makeRDD(test)
  }
}