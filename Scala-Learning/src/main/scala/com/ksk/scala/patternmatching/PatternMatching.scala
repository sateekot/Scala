package com.ksk.scala.patternmatching

class PatternMatching {

  def test(in:Any) = in match {
    case s:String => "String, length : "+s.length()
    case i:Int if i > 0 => "Natural Int"
    case i: Int => "Another int"
    case a:AnyRef => a.getClass.getName
    case _ => "null"
  }
  
  def sumOdd(in : List[Int]) : Int = in match {
    case Nil => 0
    case x::rest if x%2 ==1 => x+sumOdd(rest)
    case _::rest =>sumOdd(rest)
  }
  
  def noPairs[T](in : List[T]) : List[T] = in match {
    case Nil => Nil
    case a::b::rest if a == b => noPairs(a :: rest)
    case a::rest => a :: noPairs(rest)
  }
  
  def getStrings(in : List[Any]) : List[String] = in match {
    case Nil => Nil
    case (s: String) :: rest => s :: getStrings(rest)
    case _ ::rest => getStrings(rest)
  }
}