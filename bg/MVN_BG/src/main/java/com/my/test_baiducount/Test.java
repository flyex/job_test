package com.my.test_baiducount;

public class Test {
    public static void main(String[] args) {
        String line = "baidu.com/by/baidu";
        String[] words = line.split("\\.|/");
        for (String word:words){
            System.out.println(word);
        }
        for (int i = 0;i<words.length;i++){
            System.out.println(words[i]);
            if (words[i].equals("baidu")){
                System.out.println("6666");
            }
        }
    }
}
