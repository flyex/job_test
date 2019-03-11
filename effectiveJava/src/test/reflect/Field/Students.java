package test.reflect.Field;

public class Students {
    public int age;
    protected String name;
    private String numb;
    char sex ;

    @Override
    public String toString() {
        return "name:"+name+"--age:"+age+"--sex:"+sex+"---numb:"+numb;
    }
}
