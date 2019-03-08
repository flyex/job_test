package RegEx;

/**
 * Created by flyex on 2018/9/17.
 */
import java.util.regex.*;

class RegexExample1{
    public static void main(String args[]){
        String content = "I am noob " +
                "from runoob.com.";

        String pattern = ".*runoob.*";

        String isMatch = Pattern.matches(pattern, content)?"是":"否";
        System.out.println("字符串中是否包含了 'runoob' 子字符串?     " + isMatch);
    }
}
