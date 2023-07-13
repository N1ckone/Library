package ru.nikon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nikon.dao.LibraryDAO;

@Controller
@RequestMapping("/books")
public class BooksController {

    private LibraryDAO dao;

    @Autowired
    public BooksController(LibraryDAO dao) {
        this.dao = dao;
    }

}
