package test;

import org.springframework.stereotype.Component;

@Component("personDaoImpTwo")
public class PersonDaoImpTwo implements PersonDao {
    public void savePerson() {
        System.out.println("save Person Two");

    }
}
