package com.ksk.scala.collections

class Collections {
  def isOdd(x: Int) = {
    x%2 == 1
  }
  
  def reduceLeft(list : List[String]) = {
    list.reduceLeft((a,b) => if(a.length > b.length) a else b)
  }
}