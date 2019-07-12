package compareObject;

public class Objects {
    private int age;
    private int birth;
    private String name;
    private String kinds;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    public Objects(int age, int birth, String name, String kinds) {
        this.age = age;
        this.birth = birth;
        this.name = name;
        this.kinds = kinds;
    }
}
