package Java_Io;

import java.io.*;

/**
 * Created by flyex on 2018/9/19.
 */
public class OutPut {
    public static void main(String[] args){
        try{
            FileInputStream go = new FileInputStream("d:/1/1.txt");
            InputStreamReader go1 = new InputStreamReader(go,"gbk");
            StringBuffer a = new StringBuffer();
            while (go1.ready()){
                a.append((char) go1.read());
            }
            System.out.println(a.toString());
            go.close();
            go1.close();
        }catch(IOException e){
            System.out.println("exception");
        }

    }
}
