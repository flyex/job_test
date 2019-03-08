package Java_Io;

import java.io.*;

/**
 * Created by flyex on 2018/10/24.
 */
public class Test2 {
    public static void main(String[] args) throws Exception{
        try{
        BufferedWriter out =new BufferedWriter(new FileWriter("TEST2"));
        out.write("erere6666\n");
        out.close();
        out = new BufferedWriter(new FileWriter("TEST2",true));
        out.write("hahahahah");
        out.close();
        BufferedReader in = new BufferedReader(new FileReader("TEST2"));
        String str;
        while((str=in.readLine())!=null){
            System.out.println(str);
        }
        }catch (IOException e){
           e.printStackTrace();
        }

    }
}
