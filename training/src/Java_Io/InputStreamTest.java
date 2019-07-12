package Java_Io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InputStreamTest {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("test.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        int length;
        byte[] bytes = new byte[1024];
        while ((length=bis.read(bytes))!=-1){
            System.out.print(new String(bytes,0,length));
        }
        bis.close();
        fis.close();
    }
}
