package com.ksk.scala.caseclasses

object Main extends App{
  val person = Person("KSK",29)
  println("Person Details = "+person.name+" :: "+person.age)
  println(person.toString())
    val person1 = Person("Manuu",26)
  println("Person Details = "+person1.name+" :: "+person1.age)
  println(person1.toString())
  println(person1 == Person("Manuu",26))
}