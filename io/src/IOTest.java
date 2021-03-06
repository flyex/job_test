import java.awt.desktop.SystemSleepEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class IOTest {
    public static void fileInputStream(){
        try {
            FileInputStream file = new FileInputStream("text.txt");
            byte[] bytes = new byte[1024];
            int numb = 0;

            while ((numb = file.read(bytes))>0){
                System.out.println(new String(bytes,0,numb));
            }
            System.out.println("-------------------");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void fileReader(){
        try {
            FileReader file = new FileReader("text.txt");
            char[] ch = new char[32];
            int numb = 0;
            while ((numb =file.read(ch)) >0){
                System.out.println(new String(ch,0,numb));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("-------------------");
    }

    public static void fileOutputStream(){
        try {
            FileInputStream file = new FileInputStream("file.txt");
            FileOutputStream fout = new FileOutputStream("text.txt");
            byte[] bytes = new byte[32];
            int numb = 0;
            while ((numb = file.read(bytes))>0){
                fout.write(bytes,0,numb);
            }

            System.out.println("写入成功");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void fileWrite(){
        try(FileWriter file = new FileWriter("file.txt")){
            file.write("孤帆远影碧空尽\r\n");
            file.write("唯见长江天际流\r\n");
            System.out.println("1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        fileWrite();
        fileOutputStream();
        fileInputStream();
        fileReader();
    }
}
