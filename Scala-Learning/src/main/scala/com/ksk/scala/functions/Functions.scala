package com.ksk.scala.functions

class Functions {
  def operation(functionParam: (Int,Int) =>Int) {
    println(functionParam(4,4))
  }
  
  val add = (x: Int, y: Int) => x+y
  
  def test_add() = operation(add)
  
  val subtract = (x: Int, y : Int) => x-y
  val multiply = (x:Int, y: Int) => x*y
  
  def test_sub() = { operation(subtract) }
  def test_mul() = operation(multiply)
}