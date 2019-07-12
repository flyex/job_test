package com.flyex.demo;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;

public class FTPTest {
    public static void main(String[] args)  {
        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect("192.168.1.2", 21);
            ftpClient.login("user1", "123!@#asd");

            FileInputStream inputStream = new FileInputStream(new File("D:\\3.jpg"));
            ftpClient.changeWorkingDirectory("home/user1/myhome");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile("test.jpg", inputStream);
            ftpClient.logout();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
