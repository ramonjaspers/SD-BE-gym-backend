package gym.gymbackend.controller;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service){
        this.service = service;
    }

    @GetMapping(value = "/persons")
    public ResponseEntity<Object> getPersons() {
        List<PersonDto> persons = service.getPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping(value = "/persons/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable String id) {
        try{
            PersonDto person = service.getPerson(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Error e) {
            return new ResponseEntity<>("No person found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "/persons")
    public ResponseEntity<Object> createPerson(@Valid @RequestBody PersonDto person, BindingResult br) {
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        service.createPerson(person);
        return new ResponseEntity<>("Person created", HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/person/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable String person) {
        try {
            service.getPerson(person);
            service.deletePerson(person);
            return new ResponseEntity<>("Person removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Person does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
