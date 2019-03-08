

class Person {
    private String name;
    private int age;

    Person() {

    }

    public Person(String name, int age) {

        this.name = name;
        this.age = age;
    }

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


    public int hashCode() {

        return this.name.hashCode() + age * 37;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person p = (Person) obj;
            return this.name.equals(p.name) && this.age == p.age;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {

        return "Person@name:" + this.name + " age:" + this.age;
    }

    public static void main(String[] args){
        Person p1 = new Person("xia",123);
        Person p2 = new Person("xia",123);
        System.out.println("p1的hashcode是："+p1.hashCode()+"*******p2的hashcode是："+p2.hashCode());
        System.out.println(p1==p2);
        System.out.println(p1.equals(p2));
    }

}
