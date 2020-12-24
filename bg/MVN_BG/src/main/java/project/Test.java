package project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test {
    public static void main(String[] args) {

        String lines = "{\"events\":\"1473367236143\\u00010\\u0001connectByQRCode\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000027\\u0001\\n1473367261933\\u00010\\u0001AppLaunch\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000028\\u0001\\n1473367280349\\u00010\\u0001connectByQRCode\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000029\\u0001\\n1473367331326\\u00010\\u0001AppLaunch\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000030\\u0001\\n1473367353310\\u00010\\u0001connectByQRCode\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000031\\u0001\\n1473367387087\\u00010\\u0001AppLaunch\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000032\\u0001\\n1473367402167\\u00010\\u0001connectByQRCode\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000033\\u0001\\n1473367451994\\u00010\\u0001AppLaunch\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000034\\u0001\\n1473367474316\\u00010\\u0001connectByQRCode\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000035\\u0001\\n1473367564181\\u00010\\u0001AppLaunch\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000036\\u0001\\n1473367589527\\u00010\\u0001connectByQRCode\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000037\\u0001\\n1473367610310\\u00010\\u0001AppLaunch\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000038\\u0001\\n1473367624647\\u00010\\u0001connectByQRCode\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000039\\u0001\\n1473368004298\\u00010\\u0001AppLaunch\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000040\\u0001\\n1473368017851\\u00010\\u0001connectByQRCode\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000041\\u0001\\n1473369599067\\u00010\\u0001AppLaunch\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000042\\u0001\\n1473369622274\\u00010\\u0001connectByQRCode\\u0001\\u00010\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u0001\\u00011609072239570000043\\u0001\\n\",\"header\":{\"cid_sn\":\"1501004207EE98AA\",\"mobile_data_type\":\"\",\"os_ver\":\"22\",\"mac\":\"1c:77:f6:78:f5:75\",\"resolution\":\"1080x1920\",\"commit_time\":\"1502686418952\",\"sdk_ver\":\"103\",\"device_id_type\":\"mac\",\"city\":\"江门市\",\"device_model\":\"HUAWEI VNS-AL00\",\"android_id\":\"867830021735040\",\"carrier\":\"中国xx\",\"promotion_channel\":\"1\",\"app_ver_name\":\"1.4\",\"imei\":\"867830021735040\",\"app_ver_code\":\"4010104\",\"pid\":\"pid\",\"net_type\":\"3\",\"device_id\":\"m.1c:77:f6:78:f5:75\",\"app_device_id\":\"m.1c:77:f6:78:f5:75\",\"release_channel\":\"1009\",\"country\":\"CN\",\"time_zone\":\"28800000\",\"os_name\":\"android\",\"manufacture\":\"OPPO\",\"commit_id\":\"fde7ee2e48494b24bf3599771d7c2a78\",\"account\":\"none\",\"app_token\":\"XIAONIU_A\",\"app_id\":\"com.appid.xiaoniu\",\"language\":\"zh\",\"build_num\":\"YVF6R16303000403\"}}\n" ;

        JSONObject jsonObject = JSON.parseObject(lines);

        JSONObject headerObj = jsonObject.getJSONObject("header");

        System.out.println(headerObj.getString("city"));
    }
}
