package otherTest;

public class ListSon {
    private String name;
    private int age;
public ListSon(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public ListSon(String name,int age){
        this.age=age;
        this.name=name;
    }
}
