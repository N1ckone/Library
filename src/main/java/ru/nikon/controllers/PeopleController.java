package ru.nikon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikon.dao.LibraryDAO;
import ru.nikon.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private LibraryDAO dao;

    @Autowired
    public PeopleController(LibraryDAO dao) {
        this.dao = dao;
    }

    @GetMapping()
    public String showAllPeople(Model model) {
        model.addAttribute("people", dao.getPeople());
        return "people/show";
    }

    @GetMapping("/{id}")
    public String showPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", dao.selectPerson(id).get());
        return "people/index";
    }

    @GetMapping("/new")
    public String getAddForm(@ModelAttribute Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute Person person) {
        dao.insertPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", dao.selectPerson(id).get());
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute Person person, @PathVariable("id") int id) {
        dao.updatePerson(person, id);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        dao.deletePerson(id);
        return "redirect:/people";
    }


}
