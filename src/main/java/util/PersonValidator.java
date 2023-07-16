package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nikon.dao.LibraryDAO;
import ru.nikon.models.Person;

import javax.validation.constraints.Email;

@Component
public class PersonValidator implements Validator {
    private LibraryDAO dao;

    @Autowired
    public PersonValidator(LibraryDAO dao) {
        this.dao = dao;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(errors.hasErrors()) {

        }
    }
}
