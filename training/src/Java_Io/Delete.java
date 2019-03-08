package Java_Io;

import java.io.File;
import java.io.IOException;

/**
 * Created by flyex on 2018/10/24.
 */
public class Delete {
    public static void main(String[] args){
        try{
            File file =new File("d:/666/666/666/1.txt");
            if(file.delete()){
                System.out.println("文件已删除");
            }else{
                System.out.println("文件删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
