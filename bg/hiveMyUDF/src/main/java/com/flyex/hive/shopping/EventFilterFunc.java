package com.flyex.hive.shopping;

import org.apache.hadoop.hive.ql.exec.UDF;

public class EventFilterFunc extends UDF {

    public static boolean evaluate(String line){

        String jsonStr = line.split("AppMain - ")[1];

        if (jsonStr.contains("start")){
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

        String line = "13:51:48.265 [main] INFO  com.flyex.AppMain - 1607061108265|{\"cm\":{\"ln\":\"-93.2\",\"sv\":\"V2.3.8\",\"os\":\"8.1.5\",\"g\":\"H39Q9PGT@gmail.com\",\"mid\":\"m950\",\"nw\":\"WIFI\",\"l\":\"es\",\"vc\":\"12\",\"hw\":\"640*1136\",\"ar\":\"MX\",\"uid\":\"u240\",\"t\":\"1606973816595\",\"la\":\"-35.2\",\"md\":\"HTC-3\",\"vn\":\"1.3.3\",\"ba\":\"HTC\",\"sr\":\"P\"},\"ap\":\"com.flyex.AppMain\",\"et\":[{\"ett\":\"1607036581574\",\"en\":\"loading\",\"kv\":{\"extend2\":\"\",\"loading_time\":\"12\",\"action\":\"2\",\"extend1\":\"\",\"type\":\"2\",\"type1\":\"\",\"loading_way\":\"1\"}},{\"ett\":\"1606970926977\",\"en\":\"ad\",\"kv\":{\"entry\":\"3\",\"show_style\":\"5\",\"action\":\"1\",\"detail\":\"325\",\"source\":\"1\",\"behavior\":\"1\",\"content\":\"2\",\"newstype\":\"9\"}},{\"ett\":\"1607052084390\",\"en\":\"active_foreground\",\"kv\":{\"access\":\"\",\"push_id\":\"1\"}},{\"ett\":\"1607041683111\",\"en\":\"error\",\"kv\":{\"errorDetail\":\"java.lang.NullPointerException\\\\n    at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\\\n at cn.lift.dfdf.web.AbstractBaseController.validInbound\",\"errorBrief\":\"at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)\"}},{\"ett\":\"1606986495544\",\"en\":\"comment\",\"kv\":{\"p_comment_id\":2,\"addtime\":\"1606989414154\",\"praise_count\":429,\"other_id\":7,\"comment_id\":5,\"reply_count\":152,\"userid\":2,\"content\":\"册模脸伏忧霜潭矢杏箩幸免测臆\"}},{\"ett\":\"";

        System.out.println(evaluate(line));

    }

}
