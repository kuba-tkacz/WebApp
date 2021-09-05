package com.example.controller;

import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import com.example.service.PersonService;

import java.util.List;

@Controller
public class PersonList {

    private final PersonRepository personRepository;
    private final PersonService personService;

    public PersonList(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String getPersonList(Model model) {
        List<Person> list = personRepository.findAll();
        model.addAttribute("person", list);
        return "persons/personList";
    }

    @RequestMapping(value = {"/addNewPerson"}, method = RequestMethod.GET)
    public String getAddNewPerson() {
        return "persons/addNewPerson";
    }

    @RequestMapping(value = {"/addNewPerson"}, method = RequestMethod.POST)
    public RedirectView postAddNewPerson(@ModelAttribute Person newPerson) {
        personRepository.save(newPerson);
        return new RedirectView("/personList");
    }

    @RequestMapping(value = {"/editPerson/{id}"}, method = RequestMethod.GET)
    public String getEditPerson(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personService.getPerson(id));
        return "persons/editPerson";
    }

    @RequestMapping(value = {"/personList/{id}"}, method = RequestMethod.POST)
    public RedirectView getEditPerson(@PathVariable("id") Long id, @ModelAttribute Person newPerson) {
        personRepository.save(newPerson);
        return new RedirectView("/editPerson/{id}");
    }

    @RequestMapping(value = {"/editPerson/{id}"}, method = RequestMethod.POST)
    public RedirectView getEditPerson(@PathVariable("id") Long id) {
        personRepository.delete(personService.getPerson(id));
        return new RedirectView("/personList");
    }


}


