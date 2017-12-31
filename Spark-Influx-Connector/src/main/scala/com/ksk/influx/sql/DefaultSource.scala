package com.ksk.influx.sql

import org.apache.spark.sql.{SQLContext,DataFrame,SaveMode}
import org.apache.spark.sql.sources.{BaseRelation,RelationProvider,SchemaRelationProvider,CreatableRelationProvider}

import org.apache.spark.sql.types.StructType

class DefaultSource extends RelationProvider with SchemaRelationProvider //with CreatableRelationProvider
{
  
 override def createRelation(sqlContext: SQLContext, parameters: Map[String, String]) 
 : BaseRelation = {
   createRelation(sqlContext, parameters, null)  
 }
 
 override def createRelation(sqlContext: SQLContext, parameters: Map[String, String], schema: StructType)
 :BaseRelation = {

   val host = parameters.getOrElse("host", sys.error("Host value is manadatory"))
   val port = parameters.getOrElse("port", sys.error("Port is mandatory"))
   val dbName = parameters.getOrElse("dbName", sys.error("db name is manadatory"))
   val dbTable = parameters.getOrElse("dbTable", sys.error("Table name is required"))
   println("Table name is = "+dbTable)
   new InfluxRelation(host, port.toInt, dbName, dbTable,sqlContext)

 }
}