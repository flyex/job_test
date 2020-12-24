package com.flyex.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestIO {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024);

        String str;

        do{
            str = br.readLine();
            System.out.println(str);
        }while (!str.equals("q"));

    }
}
