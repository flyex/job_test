package com.flyex.distributesystem;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeService extends Thread {
    int port = 0;
    public TimeService(int port){
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("服务器已绑定端口："+port);
            while (true){
                Socket sc = ss.accept();
                InputStream inputStream = sc.getInputStream();
                OutputStream outputStream = sc.getOutputStream();
                outputStream.write(new Date().toString().getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
