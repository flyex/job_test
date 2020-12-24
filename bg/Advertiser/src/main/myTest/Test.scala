import redis.clients.jedis.JedisPool

object Test {

  private val jedisPool = new JedisPool("qqw",6379)

  def getJedis() = jedisPool.getResource

  def main(args: Array[String]): Unit = {

    val gg =
      """
        #床前明月光，
  #疑是地上霜。
    #举头望明月，
        #低头思故乡。
      """.stripMargin //.replaceAll("\n"," ")

    val list1 = List(1,2,3,4)
    val lsit2 = List(2,3,4,5)

    //val list3: List[Int] = (list1.:::(lsit2)).distinct


    

    println()
  }

}
