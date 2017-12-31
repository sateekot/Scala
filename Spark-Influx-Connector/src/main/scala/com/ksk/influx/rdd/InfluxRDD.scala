package com.ksk.influx.rdd

import scala.collection.Iterator
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.ClassTag

import org.apache.spark.Partition
import org.apache.spark.SparkContext
import org.apache.spark.TaskContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

import com.ksk.influx.util.Util
import com.paulgoldbaum.influxdbclient.InfluxDB
import com.paulgoldbaum.influxdbclient.QueryResult



class InfluxRDDPartition(override val index: Int,
                         val query: Option[String]=None
) extends Partition
case class ExplainRow(selectType: String, extra: String, query: String)
case class InfluxRDD[Row: ClassTag](@transient sc : SparkContext , 
                                  sql: String,
                                  sqlParams: Seq[Any] = Nil,
                                  databaseName: Option[String] = None,
                                  disablePartitionPushdown: Boolean = false,
                                  enableStreaming: Boolean = false,    
                                  schema : StructType
                                  ) extends RDD[Row](sc,Nil) {
  override def getPartitions: Array[Partition] = {
    if(disablePartitionPushdown) {
      getSinglePartition
    } else {
      getSinglePartition
    }
    }
  private def getSinglePartition: Array[Partition] = {
    Array[Partition](new InfluxRDDPartition(0))
}
  
  override def compute(partition : Partition, context : TaskContext) : Iterator[Row] = new NextIterator[Row] {

    val fields = schema.fields
    val influxdb = InfluxDB.connect("localhost", 8086)
    val database = influxdb.selectDatabase("ksk")
    val result: Future[QueryResult] = database.query("SELECT * FROM treasures")
    val rows = result.map(fileContent => {
      val data = fileContent.series.head.records.map(line => line.allValues.toSeq)
      val tmp = data.map(words => words.zipWithIndex.map {
        case (value, index) =>
          val colName = fields(index).name
          Util.castTo(
            value.toString(),
            fields(index).dataType)
      })
      tmp.map(s => Row.fromSeq(s))
    })

    override def getNext: Row = {

      finished = true
      null.asInstanceOf[Row]
    }

    override def close() {
    }
  }
}
