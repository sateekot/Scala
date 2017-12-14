package com.ksk.scala.companion.objects

object Main extends App{
  val circle = Shape(2)
  println("Circle Area = "+circle.area)
  
  val rectangle = Shape(2,3)
  println("Rectanble Area = "+rectangle.area)
  
}