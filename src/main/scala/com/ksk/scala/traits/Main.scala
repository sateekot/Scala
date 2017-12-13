package com.ksk.scala.traits

object Main extends App{
  
  val batMobile = new BatMobile(300)
  println("Speed= "+batMobile.mph)
  batMobile.race()
  batMobile.fly()
  batMobile.gliding()
  
}