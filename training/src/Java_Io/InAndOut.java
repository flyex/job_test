package Java_Io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InAndOut {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("C:\\Users\\zhixin.lv\\Desktop\\简.txt");
        //BufferedInputStream bis = new BufferedInputStream(fis);
        /**FileOutputStream fos = new FileOutputStream("inAndOutCopy.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] bytes = new byte[1024];
        int len;
        while ((len=bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
        }
        bis.close();
        fis.close();
        bos.close();
        fos.close();**/
        byte[] bytes = new byte[1024];
        int lenth;
        StringBuffer stringBuffer = new StringBuffer();
        while ((lenth=fis.read(bytes))!=-1){
            stringBuffer.append(new String(bytes,0,lenth,"gbk"));
        }
        System.out.println(stringBuffer);
    }
}
