package com.flyex.test

object BaseTest {

  def main(args: Array[String]): Unit = {

    //TODO list
    val l1: List[Int] = 1::2::3::Nil
    val l2: List[Int] = Nil.::(1).::(2).::(3)
    val l11 = l1.+:(4):+(5)
//    l1.foreach(println(_))
//    l2.foreach(println(_))
//    l11.foreach(println(_))
    val l21 = 5::l2
    l21.foreach(a => a)

    //TODO 隐式类
    val mysql = new Mysql
    mysql.insert()
    mysql.delete()
    //TODO 隐式参数
    implicit val name: String = "xiaohua"
    def fun(implicit name: String = "xiaoli"): Unit ={
      //println(name + "hello")
    }
    //fun()

    //TODO for for
    for (i <- 1 to 3; j <- 1 to 3; if i!=j){println(i+j)}
  }

  implicit class DB(sql:Mysql){
    def delete(){}
  }
  class Mysql{
    def insert(){}
  }

}


