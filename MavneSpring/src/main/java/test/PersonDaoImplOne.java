package test;


import org.springframework.stereotype.Component;

@Component
public class PersonDaoImplOne implements PersonDao {
    public void savePerson() {
        System.out.println("save Person One");

    }
}
