package Java_Io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by flyex on 2018/9/18.
 */
public class BrRead {
    public static void main(String[] args) throws IOException {
        char a;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder();
        System.out.println("输入字符，输q退出");
        do{

            a =(char) br.read();
            str.append(a);

        }while(a!='q');
        System.out.print(str);
    }
}
