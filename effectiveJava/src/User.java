public class User {
    int age;
    String name;
    public User(int age,String name){
        this.age=age;
        this.name=name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object O){
        if (!(O instanceof User))
            return false;
        return age==((User) O).age&&name==((User) O).name;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31*result+age;
        result = 31*result+name.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "name:"+name+"age:"+age;
    }
}
