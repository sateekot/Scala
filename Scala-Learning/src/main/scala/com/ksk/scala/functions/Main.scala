package com.ksk.scala.functions

object Main extends App {
  val double = (i: Int) => i*i
  println(double(4))
  
  val value = new Functions
  value.test_add()
  value.test_sub()
  value.test_mul()
}