package ru.nikon.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.nikon.dao.BookDAO;
import ru.nikon.models.Book;

@Component
public class BookValidator implements Validator {
    private final BookDAO dao;

    @Autowired
    public BookValidator(BookDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if(dao.selectBook(book.getName()).isPresent()) {
            errors.rejectValue("name", "", "Такая книга уже добавлена");
        }
    }
}
