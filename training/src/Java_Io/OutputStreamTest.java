package Java_Io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class OutputStreamTest {
    public static void main(String[] args) throws Exception {
        String in = "啊哈哈哈哈'\r\n'一库一库";
        OutputStream os = new FileOutputStream("testout.txt");
        BufferedOutputStream bos = new BufferedOutputStream(os);
        bos.write(in.getBytes());
        bos.close();
        os.close();
    }
}
