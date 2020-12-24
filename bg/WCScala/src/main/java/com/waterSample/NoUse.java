package com.waterSample;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.util.StringTokenizer;

public class NoUse {

    @Test
    public void test1() {
        char a = '小';
        System.out.println(a + 0);
    }

    //TODO FileInputStream使用
    @Test
    public void fileByteStream() throws Exception {

        //字节流
        FileInputStream inputStream = new FileInputStream(new File("D:\\ip.txt"));
        //字符流
        FileReader fileReader = new FileReader("path");

        //InputStreamReader类可以指定字符编码,内传参为字节流
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

        //带缓冲的字节流
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 32);
        //带缓冲的字符流
        BufferedReader bufferedReader = new BufferedReader(fileReader,32);
        BufferedReader bufferedReade2r = new BufferedReader(reader,32);







        FileOutputStream outputStream = new FileOutputStream(new File("D:\\ip_bak.txt"), true);
        //OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");


        //IOUtils.copy(inputStream,outputStream);

        char[] buff = new char[512];
//        while (reader.read(buff,0,buff.length)!= -1){
//            writer.write(buff,0,buff.length);
//        }


        outputStream.flush();
        inputStream.close();
        outputStream.close();

    }

    @Test
    public void fileCharacterStream() throws Exception {

        FileReader fileReader = new FileReader("D:\\ip.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter("D:\\ip_bak2.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


//        char[] buff = new char[1];
//
//        while (fileReader.read(buff)!= -1){
//            fileWriter.write(buff);
//        }
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line + "\n");
        }

        bufferedWriter.flush();
        fileWriter.flush();
        fileReader.close();
        fileWriter.close();

    }

    @Test
    public void characterFun() throws Exception{
        FileReader fileReader = new FileReader("D:\\ip.txt");

        FileWriter fileWriter = new FileWriter("D:\\ip_bak2.txt");

        char[] buff = new char[4096];
        int len = 0;
        while ((len = fileReader.read(buff)) != -1){
            fileWriter.write(buff,0,len);
        }

        fileReader.close();
        fileWriter.close();

    }
    @Test
    public void binaryTest() throws Exception{
        byte[] bytes = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\nouse.txt"));

        fileOutputStream.write(bytes);

        fileOutputStream.close();

        FileInputStream in = new FileInputStream(new File("d:\\nouse.txt"));

        int r;
        while ((r = in.read()) != -1){
            System.out.println(r);
        }

        in.close();

    }

}
