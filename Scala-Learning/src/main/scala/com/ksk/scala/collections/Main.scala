package com.ksk.scala.collections

object Main extends App{
  
  val obj = new Collections
  val list = 1 :: 2 :: 3 :: 4 :: 5 :: 6:: 7 :: 8 ::Nil
  val newOddList = list.filter(obj.isOdd)
  println("Odd Elements List = "+newOddList)
  
  println("99 Red Ballons".toList.filter(Character.isDigit))
  
  val listWords = "moose" :: "cow" :: "A" :: "cat" :: Nil
  println("Max String is = "+obj.reduceLeft(listWords))
  
  val stream = 1 #:: 2 #:: 3 #:: Stream.Empty
  println(stream.length)
}