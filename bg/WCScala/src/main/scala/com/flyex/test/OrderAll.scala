package com.flyex.test

object OrderAll{
  implicit object OrderingAnimals extends Ordering[Animals]{
    override def compare(x: Animals, y: Animals): Int = {
      y.age - x.age
    }
  }
}
