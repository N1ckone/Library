package ru.nikon.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nikon.dao.PersonDAO;
import ru.nikon.models.Person;

@Component
public class PersonValidator implements Validator {
    private PersonDAO dao;

    @Autowired
    public PersonValidator(PersonDAO dao) {
        this.dao = dao;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (dao.selectPerson(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Такой пользователь уже зарегистрирован");
        }
    }
}
