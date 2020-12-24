package com.flyex.Text2Parquet;

import java.util.StringTokenizer;

public class Test {

    public static void main(String[] args) {

        String writeSchema = "message example {\n" +
                "required binary id;\n" +
                "required binary name;\n" +
                "}";

        StringTokenizer tokenizer = new StringTokenizer(writeSchema, " ;{}()\n\t", true);

        String w1 = tokenizer.nextToken();
        String w2 = tokenizer.nextToken();
        String w3 = tokenizer.nextToken();
        System.out.print(w1+" : " +w2+":"+w3);
    }
}
