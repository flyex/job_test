package com.kafka.review

import kafka.utils.ZKGroupTopicDirs
import org.I0Itec.zkclient.ZkClient

object ZookeeperTest {

  def main(args: Array[String]): Unit = {

    val groupId = "flyex"
    val topic = "flyex_vip"
    val zkQurom = "hdp-02:2181,hdp-03:2181,hdp-04:2181"

    val groupTopicDirs = new ZKGroupTopicDirs(groupId,topic)
    val zkTopicPath = s"${groupTopicDirs.consumerOffsetDir}"

    print(zkTopicPath.toString)

    val client = new ZkClient(zkQurom)
    val count = client.countChildren(zkTopicPath)

    println(count)


  }

}
