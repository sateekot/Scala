name := "Spark-Influx-Connector"

version := "1.0"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq( 
  "org.apache.spark" % "spark-core_2.11" % "2.2.0" exclude("io.netty", "netty-all"),
  "org.apache.spark" % "spark-sql_2.11" % "2.2.0",
  "com.paulgoldbaum" % "scala-influxdb-client_2.11" % "0.5.1"
)

assemblyMergeStrategy in assembly := {
 case PathList("META-INF", xs @ _*) => MergeStrategy.discard
 case x => MergeStrategy.first
}