package otherTest;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Student {
    private Person person = new Person();
    public String toString(){
        return "["+person+"]";
    }
    public static void main(String[] args){
        Student student = new Student();
        System.out.println(student.toString());
    }
}
