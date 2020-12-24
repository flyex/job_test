package dayday.base

object ImplicitTest {

  def matchCase(x : Any) = x match {
    case x:Int => println(x.toInt)
    case x: Object => println(x.getClass.toGenericString)
  }

  def main(args: Array[String]): Unit = {

    val stu = new Student



    implicit def transform(students: Student): ExtendStu = {
      new ExtendStu
    }

    stu.study()
    stu.play()

    matchCase(1)

    matchCase(stu)

  }
}
