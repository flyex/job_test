package Java_Io;

import java.io.File;

/**
 * Created by flyex on 2018/10/25.
 */
public class GetFileLength {
    public static long getLength(String fileName){
        File file = new File(fileName);
        if(!file.exists()||!file.isFile()){
            System.out.println("文件不存在");
            return -1;
        }
        return file.length();
    }
    public static void main(String[] args){
        long size = getLength("d:/666/666/666/1.txt");
        System.out.println(size);
    }
}
