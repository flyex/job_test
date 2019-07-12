package com.flyex.io;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        FileInputStream ins = new FileInputStream("D:\\project\\bg\\test\\src\\access.log");
        FileOutputStream ops = new FileOutputStream("D:\\project\\bg\\test\\src\\hdfs.txt");
        System.out.println(ins.read());
        /**byte[] buf = new byte[1024];
        int read = 0;
        while ((read=ins.read(buf))!=-1){
            String s = new String(buf,0,read);
            System.out.println(s);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(ins));
        String line = null;
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }

        byte[] buf = new byte[1024];
        int write = 0;
        while ((write=ins.read(buf))!=-1){
            ops.write(buf,0,write);
        }**/

        BufferedReader br = new BufferedReader(new InputStreamReader(ins));
        FileWriter fileWriter = new FileWriter("D:\\project\\bg\\test\\src\\hdfs.txt");
        BufferedWriter writer = new BufferedWriter(fileWriter);
        String s = null;
        while ((s = br.readLine())!=null){
            writer.write(s+"\n");
        }
        writer.close();
        br.close();
    }
}
