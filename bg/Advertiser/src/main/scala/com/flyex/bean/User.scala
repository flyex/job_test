package com.flyex.bean

class User(val name: String,
           val age: Integer,
           val sex: String,
           val habit: String) extends Product with Serializable {

  //角标和成员属性的对应关系
  override def productElement(n: Int): Any = n match {
    case 0 => name
    case 1 => age
    case 2 => sex
    case 3 => habit
  }


  override def productArity: Int = 4

  override def canEqual(that: Any): Boolean = that.isInstanceOf[User]
}

object User {
  //伴生对象
  def apply(arr: Array[String]): User = new User(
    arr(0),
    arr(1).toInt,
    arr(2),
    arr(3)
  )
}


