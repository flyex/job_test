package demo

object Test3 {

  def main(args: Array[String]): Unit = {

    implicit def pAsia(people: People): AsiaPeo = new AsiaPeo()

    val lihui = new People()
    lihui.say()

  }

}

class People()
class AsiaPeo(){
  def say() = println("i am ASIA")
}
