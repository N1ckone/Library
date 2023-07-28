package ru.nikon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nikon.dao.BookDAO;
import ru.nikon.dao.PersonDAO;
import ru.nikon.models.Book;
import ru.nikon.models.Person;
import ru.nikon.util.BookValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private BookDAO dao;
    private PersonDAO personDAO;
    private BookValidator validator;


    @Autowired
    public BooksController(BookDAO dao, PersonDAO personDAO, BookValidator validator) {
        this.dao = dao;
        this.personDAO = personDAO;
        this.validator = validator;
    }

    @GetMapping()
    public String showAllBooks(Model model) {
        model.addAttribute("books", dao.getBooks());
        return "books/show";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") int id) {
        Book book = (Book) dao.selectBook(id).get();
        model.addAttribute("book", book);
        model.addAttribute("person", dao.getBookOwner(book.getName()));
        model.addAttribute("people", personDAO.getPeople());
        return "books/index";
    }

    @GetMapping("/new")
    public String getAddForm(@ModelAttribute Book book) {
        return "books/new";
    }

    @PostMapping()
    public String insert(@ModelAttribute @Valid Book book, BindingResult bs) {
        validator.validate(book, bs);
        if(bs.hasErrors()) {
            return "books/new";
        }
        dao.insertBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", dao.selectBook(id).get());
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute @Valid Book book, BindingResult bs, @PathVariable("id") int id) {
        if(bs.hasErrors()) {
            return "books/edit";
        }
        dao.updateBook(book, id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        dao.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/appoint")
    public String appoint(@ModelAttribute Person person, @PathVariable("id") int id) {
        dao.appoint(id, person.getId());
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/leave")
    public String leave(@PathVariable("id") int id) {
        dao.leave(id);
        return "redirect:/books/{id}";
    }


}
