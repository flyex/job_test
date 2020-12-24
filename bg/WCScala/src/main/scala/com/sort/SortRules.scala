package com.sort

object SortRules  {

  implicit object Ordering1 extends Ordering[FvAndAge]{
    override def compare(x: FvAndAge, y: FvAndAge): Int = {
      if (x.fv==y.fv){
        x.age-y.age
      }else{
        y.fv-x.fv
      }
    }
  }
}
