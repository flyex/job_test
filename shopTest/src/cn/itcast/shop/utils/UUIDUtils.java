package cn.itcast.shop.utils;

import java.util.UUID;

/**
 * 获得随机字符串方法   该包为工具包
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
