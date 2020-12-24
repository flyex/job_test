package demo

object MatchCase {

  def main(args: Array[String]): Unit = {

    def contentMatch(str: String) = str match {
        case "hello" => println("hello")
        case "1" => println("1")
        case _ => println("匹配不上")
    }

    def AnyMatch(any: Any) = any match {
        //数据类型
      case x:Int => println(s"$x")
      case y:Long => println(s"$y")
      case z:Boolean => println(s"$z")

        //数组匹配
      case Array(0) => println("只有一个元素0的数组")
      case Array(0,_) => println("以0开头，任意结尾（可有可无）的2个元素的数组")
      case Array(0,_*) => println("以0开头，任意结尾（可有可无）的1+N个元素的数组")
        //List匹配
      case 7::9::Nil => println("只有7,9元素的list")
      case x::y::z::Nil => println("只有三个元素的list")
      case m::n if n.length >2 => println("拥有头和尾，且尾部的元素数量>2的List")
        //元组匹配
      case (0,_) => println("元组第一元素0，第二元素为任意类型的数据")
      case (x,y,z) => println("拥有3元素元组")
      case (_,"a") => println("_._2为a的元组")
        //对象匹配
      case SingASong(x,y) => println(s"$x sing $y")
      case MatchObject => println("MatchObject")
    }
    AnyMatch(SingASong("小李","国歌"))
  }
}
case class SingASong(singer:String,song:String)
case object MatchObject