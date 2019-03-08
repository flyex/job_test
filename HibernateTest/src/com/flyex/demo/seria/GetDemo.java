package com.flyex.demo.seria;

import com.flyex.bean.User;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class GetDemo {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("foo.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object o = objectInputStream.readObject();
            System.out.println(o);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
