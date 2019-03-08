package Java_Io;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created by flyex on 2018/10/25.
 */
public class GetDirLength {
    public static void main(String[] args){
        long size = FileUtils.sizeOfDirectory(new File("d:/jar"));
        System.out.println(size);
    }
}
