package com.example.service;

import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersonList(){
        return personRepository.findAll();
    }
    public void deletePerson(Long id){
        personRepository.deleteById(id);
        System.out.println("deleting person on id:" + id);
    }
    public Person getPerson(Long id){
        return personRepository.findById(id).orElse(null);
    }
    public void editPerson(Person person, Long id) {
        Person editPerson = new Person(
                id,
                person.getFirstName(),
                person.getLastName(),
                person.getGitHub(),
                person.getStart(),
                person.getJava(),
                person.getBestPractice(),
                person.getTdd(),
                person.getQuestion(),
                person.getHibernate(),
                person.getHtml(),
                person.getJsp(),
                person.getThymeleaf(),
                person.getGit(),
                person.getCheckbox(),
                person.getTasks()
        );
        personRepository.save(editPerson);
        System.out.println("editing person on id:" + id);
    }
}
