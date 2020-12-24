package com;

import org.jcodings.util.ArrayCopy;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Test_t {
    @Test
    public void sockTest() throws Exception{

        String ip = InetAddress.getLocalHost().getHostAddress();
        Socket socket = new Socket("192.168.66.134", 1234);

        OutputStream out = socket.getOutputStream();

        out.write(("远程地址是"+ip+"\n"+"输入内容为"+"哈哈").getBytes());
        out.flush();
        socket.close();
    }

    @Test
    public void map2String() {
        String s = "{cf1=xiaoliu}";
        String s1 = s.substring(5, s.length() - 1);
        System.out.println(s1);
    }

    @Test
    public void stringTest(){
        final String ss = "a";
        final String s3 = "aa" + ss;
        String s = "aaa";
        String s1 = new String("aaa");
        String s2 = s1.intern();
        System.out.println(s3==s2);
    }

    @Test
    public void arrLength(){
        String word = "中国";

        int len = word.length();
        byte[] wordBytes = new byte[len];

        wordBytes = word.getBytes();

        String word2 = new String(wordBytes);

        char[] c = new char[1024];

        char[] c2 = word2.toCharArray();
//
//        c = c2;

        System.arraycopy(c2,0,c,0,c2.length);
        System.out.println(new String(c));
    }

    @Test
    public void testNIO() throws Exception{
        String infile = "D:\\access2.log";
        String outfile = "D:\\access2bak.log";

        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){
            int r = fcin.read(buffer);
            if (r == -1){
                break;
            }

            buffer.flip();

            fcout.write(buffer);

            buffer.clear();
        }
    }
}
