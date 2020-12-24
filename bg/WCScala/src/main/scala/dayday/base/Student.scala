package dayday.base

class Student {

  def study() = {
    println("我爱学习")
  }

}

class ExtendStu {
  def play(): Unit ={
    println("我也会玩")
  }
}

package object extendApp {
  implicit class ExtendStu2(stu: Student){
    def play2(): Unit ={
      println("我也会玩2")
    }
  }
}

