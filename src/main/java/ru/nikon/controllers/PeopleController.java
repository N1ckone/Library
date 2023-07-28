package ru.nikon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nikon.dao.PersonDAO;
import ru.nikon.models.Person;
import ru.nikon.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO dao;
    private PersonValidator validator;

    @Autowired
    public PeopleController(PersonDAO dao, PersonValidator validator) {
        this.dao = dao;
        this.validator = validator;
    }

    @GetMapping()
    public String showAllPeople(Model model) {
        model.addAttribute("people", dao.getPeople());
        return "people/show";
    }

    @GetMapping("/{id}")
    public String showPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", dao.selectPerson(id).get());
        model.addAttribute("books", dao.getBooksList(id));
        return "people/index";
    }

    @GetMapping("/new")
    public String getAddForm(@ModelAttribute Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute @Valid Person person, BindingResult bs) {
        validator.validate(person, bs);
        if(bs.hasErrors()) {
            return "people/new";
        }
        dao.insertPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", dao.selectPerson(id).get());
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute @Valid Person person, BindingResult bs, @PathVariable("id") int id) {
        if(bs.hasErrors()) {
            return "people/edit";
        }
        dao.updatePerson(person, id);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        dao.deletePerson(id);
        return "redirect:/people";
    }


}
