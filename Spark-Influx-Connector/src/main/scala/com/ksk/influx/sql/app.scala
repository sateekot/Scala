package com.ksk.influx.sql

import scala.collection.immutable.HashMap

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object app extends App {

  
   println("Application started.......")

  val conf = new SparkConf().setAppName("spark-custom-datasource")
  val spark = SparkSession.builder().config(conf).master("local").getOrCreate()

  val df = spark.sqlContext.read.format("com.ksk.influx.sql")
  .options(HashMap("host"->"localhost","port"-> "8086","dbName"->"ksk","dbTable" -> "treasures"))
  .load()

  df.printSchema()

}