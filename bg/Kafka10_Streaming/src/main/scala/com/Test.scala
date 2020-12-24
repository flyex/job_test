package com

object Test {
  def main(args: Array[String]): Unit = {

    def count(a:Int,b:Int):Int = a+b
    //print(count(1,2))

    val fx:(Int,Int) => Int = (x,y) => x+y
    val fx2 = (x:Int,y:Int) => x+y

    def count2(fx3:(Int,Int)=>Int,x:Int,y:Int) = { fx3(x,y) }
    //print(count2(fx,1,2))

    def count3(a:Int = 3,b:Int = 4) = a+b
    //print(count3( 5))

    val fxSum:(Int) => Int = x=>x+4
    def testSum(f:Int, x:Int) = f+4

    //print(testSum(fxSum(2),4))

    val list1:List[Int] = 1::(2::(3::Nil))
    val list2:List[Int] = Nil.::(1).::(2).::(3)

    val list11 = 8::9::list1.+:(4)

    list1.foreach(x=>print(x))
    list2.foreach(x=>print(x))
    list11.foreach(x=>print(x))



  }

}
