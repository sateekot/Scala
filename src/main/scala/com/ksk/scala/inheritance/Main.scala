package com.ksk.scala.inheritance

object Main extends App{
  
  val vehicle1 = new Car(100)
  //println("Car :: "+vehicle1.race())
  vehicle1.race()
  
  val vehicle2 = new Bike(60)
  //println("Bike :: "+vehicle2.race())
  vehicle2.race()
}