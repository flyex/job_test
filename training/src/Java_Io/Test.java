package Java_Io;

import java.io.*;

/**
 * Created by flyex on 2018/10/24.
 */
public class Test {
    public static void main(String[] args){
        try{
            InputStream in =new FileInputStream("D:/1/1.log");
            InputStreamReader inPut1 = new InputStreamReader(in,"GBK");
            StringBuffer str = new StringBuffer();
            while(inPut1.ready()){
               str.append((char)inPut1.read());
            }
            System.out.println(str.toString());
            in.close();
            inPut1.close();
        }catch(IOException e){ }
    }
}
