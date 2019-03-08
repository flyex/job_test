package StrBuffer;

/**
 * Created by flyex on 2018/9/13.
 */
public class Test {
    public static void main(String[] args){
        StringBuffer str = new StringBuffer("百度官网是:");
        str.append("www");
        str.append(".baidu");
        str.append(".com");
        System.out.println(str);
    }
}
