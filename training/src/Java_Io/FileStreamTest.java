package Java_Io;

import java.io.*;

/**
 * Created by flyex on 2018/9/18.
 */
public class FileStreamTest {
    public static void main(String[] args){
        try{
            String[] a ={"123","皮皮喜","德玛西亚","亚马逊"};
            OutputStream out = new FileOutputStream("D:/迅雷下载/test.txt");
            OutputStreamWriter out1 = new OutputStreamWriter(out,"utf-8");
            for(int i=0;i<a.length;i++){
                out1.write(a[i]+"   ");
            }
            out1.close();
            out.close();

            InputStream inPut = new FileInputStream("D:/迅雷下载/test.txt");
            InputStreamReader inPut1 = new InputStreamReader(inPut,"utf-8");

            StringBuffer inPut2 = new StringBuffer();
            while(inPut1.ready()){
                inPut2.append((char)inPut1.read());
            }
            System.out.println(inPut2.toString()+"   ");
            inPut.close();
            inPut1.close();
        } catch (IOException e){
            System.out.println("EXCEPTION");
        }
    }
}
