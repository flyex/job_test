package Java_Io;

import java.io.File;

/**
 * Created by flyex on 2018/9/19.
 */
public class CreatDir {
    public static void main(String args[]) {
        String dirname = "d:/666/666/666";
        File d = new File(dirname);
        // 现在创建目录
        d.mkdirs();
    }
}