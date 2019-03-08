package Java_Io;

import java.io.*;

/**
 * Created by flyex on 2018/9/19.
 */
public class MyTest {
    public static void main(String[] args){
        try{
            OutputStream go = new FileOutputStream("go.txt");
            String a = "一个主要";
            byte b[] = a.getBytes();
            go.write(b);

            Writer go1 = new FileWriter("go1.txt");
            String a1 = "两个主要";
            go1.write(a1);
            go1.close();
        } catch (IOException e){
            System.out.println("exception");
        }
    }
}
