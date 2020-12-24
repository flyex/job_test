package test;

public class Test {

    public static void main(String[] args) {

        String a2 = "a";
        String a = "a";
        final String b = "b";
        String a3 = new String("ab");
        String a4 = new String("ab");


        String w1 = "a"+"b";
        String w2 = "ab";
        String w3 = a+"b";
        String w4 = "a"+b;


        System.out.println(w1==w2);
        System.out.println(w1==w3);
        System.out.println(a==a2);
        System.out.println(w2==w3);
        System.out.println(w2==w4);
        System.out.println(a3==a4);

    }
}
