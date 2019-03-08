package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonService {
    @Autowired
    @Qualifier("personDaoImpTwo")
    private PersonDao personDao = new PersonDaoImpTwo() ;

    public void savePerson() {
        this.personDao.savePerson();
    }

    public static void main(String[] args){


        PersonService personService = new PersonService();
        personService.savePerson();
    }
}
