package com.ksk.scala.traits

import com.ksk.scala.inheritance.Vehicle

class BatMobile(speed : Int) extends Vehicle(speed) with Flying with Gliding{
  override val mph : Int = speed
  override def race() = println("Racing BatMobile")
  override def fly() = println("Flying BatMobile")
}