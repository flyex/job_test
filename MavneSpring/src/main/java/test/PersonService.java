package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonService {

    private PersonDao personDao ;

    @Autowired
    @Qualifier("")
    public void setPersonDao(PersonDao personDao){
        this.personDao = personDao;
    }

    public void savePerson() {
       personDao.savePerson();
    }

    public static void main(String[] args){


        PersonService personService = new PersonService();
        personService.savePerson();
    }
}
