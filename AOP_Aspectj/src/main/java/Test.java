public class Test {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public int hashCode(){
        return i%10;
    }

    public static void main(String[] args){
        String i = "的吗";
        String j = "的吗";
        String a = new String("的吗");
        boolean k = (i==a);
        System.out.println(i.equals(j)+"------"+k+"--------"+i.hashCode()+"-----------"+j.hashCode()+"********"+a.hashCode());
    }
}
