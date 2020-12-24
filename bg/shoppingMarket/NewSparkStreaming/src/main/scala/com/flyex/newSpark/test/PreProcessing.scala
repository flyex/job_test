package com.flyex.newSpark.test

import com.alibaba.fastjson.JSON

object PreProcessing {

  def main(args: Array[String]): Unit = {

    val line = "[main] INFO  com.flyex.AppMain - 1608085542740|{\"cm\":{\"ln\":\"-47.5\",\"sv\":\"V2.3.5\",\"os\":\"8.0.6\",\"g\":\"RTRLV89P@gmail.com\",\"mid\":\"m282\",\"nw\":\"3G\",\"l\":\"en\",\"vc\":\"3\",\"hw\":\"640*960\",\"ar\":\"MX\",\"uid\":\"u404\",\"t\":\"1608050679714\",\"la\":\"9.3\",\"md\":\"sumsung-17\",\"vn\":\"1.0.0\",\"ba\":\"Sumsung\",\"sr\":\"O\"},\"ap\":\"com.flyex.AppMain\",\"et\":[{\"ett\":\"1608062713981\",\"en\":\"display\",\"kv\":{\"newsid\":\"n547\",\"action\":\"1\",\"extend1\":\"1\",\"place\":\"4\",\"category\":\"94\"}},{\"ett\":\"1608038198940\",\"en\":\"loading\",\"kv\":{\"extend2\":\"\",\"loading_time\":\"12\",\"action\":\"1\",\"extend1\":\"\",\"type\":\"3\",\"type1\":\"\",\"loading_way\":\"1\"}},{\"ett\":\"1608080780561\",\"en\":\"active_foreground\",\"kv\":{\"access\":\"\",\"push_id\":\"2\"}},{\"ett\":\"1608047266571\",\"en\":\"error\",\"kv\":{\"errorDetail\":\"java.lang.NullPointerException\\\\n    at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\\\n at cn.lift.dfdf.web.AbstractBaseController.validInbound\",\"errorBrief\":\"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\"}},{\"ett\":\"1608006488179\",\"en\":\"favorites\",\"kv\":{\"course_id\":9,\"id\":0,\"add_time\":\"1608034408637\",\"userid\":1}},{\"ett\":\"1608030170228\",\"en\":\"praise\",\"kv\":{\"target_id\":2,\"id\":6,\"type\":2,\"add_time\":\"1608071710459\",\"userid\":4}}]})"

    val line2: String = line.split("AppMain - ")(1).split("\\|")(1)

    val jsonLine = line2.substring(0,line2.length-1)

    val json = JSON.parseObject(jsonLine)

    println(json.getJSONArray("et").size())

  }

}
