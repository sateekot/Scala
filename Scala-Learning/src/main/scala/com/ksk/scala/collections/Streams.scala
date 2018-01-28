package com.ksk.scala.collections

class Streams {
  def sumSq(in: List[Double]): (Int, Double, Double) = {
    in.foldLeft((0, 0d, 0d ))((t,v) => (t._1+1, t._2+v, t._3+ v* v))
  }
  
  //using pattern matching
  
  def sumSqPm(in : List[Double]) : (Int, Double, Double) = {
    in.foldLeft((0,0d,0d)) {
      case ((cnt, sum, sqSum), v) => (cnt+1, sum+v, sqSum+v*v)
    }
  }
}