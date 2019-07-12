package RegEx;

public class ConvertLetter_Num {
    static String reg = "[a-zA-Z]";
    StringBuffer strbuff = new StringBuffer();
    static String a="AAAaaaCc";
    static char abc = (char)80;
    static int num = (int)'P';
    static byte[] p = "P".getBytes();

    short aShort = 1;
    int anInt = aShort;

    int anInt2 = 1;
    short aShort2 = (short) anInt2;


    public static void main(String[] args) {
        a=a.toLowerCase();
        System.out.println(a);
        for(char aa:a.toCharArray()){
            if (String.valueOf(aa).matches(reg)){
            }
        }
        System.out.println(abc);
        System.out.println(num);
        System.out.println(p[0]);
    }
}
