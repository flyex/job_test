package com.flyex.demo;

import com.flyex.object.Student;
import com.flyex.util.ObjectUtil;

public class Demo {
    public static void main(String[] args) throws Exception {
        Student student = new Student("小李",14);
        byte[] bytes = ObjectUtil.objectToBytes(student);
        System.out.println(bytes);
        Student student2 = (Student)ObjectUtil.bytesToObject(bytes);
        System.out.println(student2);
    }
}
