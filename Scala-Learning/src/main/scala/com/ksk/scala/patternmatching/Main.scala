package com.ksk.scala.patternmatching

object Main extends App{
  
  val anyList = List(7,"KSK",6.6,'M')
  for(element <- anyList) {
    element match {
      case i:Int => println("Integer : "+i)
      case d:Double => println("Double :"+d)
      case s:String => println("String : "+s)
      case other => println("Other : "+other)
    }
  }
  
  
  val pm = new PatternMatching
  println(pm.test("Sateesh"))
  println(pm.test(-10))
  
  // add odd numbers in the lis
  val result = pm.sumOdd(List(1,2,3,4,5,6,7))
  println("Odd result = "+result)
  
  // no pairs in list
  val pairResult = pm.noPairs(List(1,1,1,2,2,2,2,3,3,4,5,5,6,2,1))
  println("Pair Result :: "+pairResult)
  
  
  //get Strings from list
  val list = pm.getStrings(List(1,2,3,"KSK","Manuu",4,5,"Lakshmi",7,"Hareesh"))
  println("List with Strings = "+list)
}