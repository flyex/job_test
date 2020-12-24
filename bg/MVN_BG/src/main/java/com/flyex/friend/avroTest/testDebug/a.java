package com.flyex.friend.avroTest.testDebug;

public class a {
    public void add(String x,int y){
        int a = b.string2Int(x);
        int aa = b.intadd1(y);
        System.out.println(a + aa);
    }

    public static void main(String[] args) {
        a a1 = new a();

        a1.add("56",23);
    }
}
