package ru.nikon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikon.dao.LibraryDAO;
import ru.nikon.models.Book;

@Controller
@RequestMapping("/books")
public class BooksController {

    private LibraryDAO dao;

    @Autowired
    public BooksController(LibraryDAO dao) {
        this.dao = dao;
    }

    @GetMapping()
    public String showAllBooks(Model model) {
        model.addAttribute("books", dao.getBooks());
        return "books/show";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", dao.selectBook(id).get());
        return "books/index";
    }

    @GetMapping("/new")
    public String getAddForm(@ModelAttribute Book book) {
        return "books/new";
    }

    @PostMapping()
    public String insert(@ModelAttribute Book book) {
        dao.insertBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", dao.selectBook(id).get());
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute Book book, @PathVariable("id") int id) {
        dao.updateBook(book, id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        dao.deleteBook(id);
        return "redirect:/books";
    }


}
