package gym.gymbackend.controller;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.model.Person;
import gym.gymbackend.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getPersons() {
        return ResponseEntity.ok().body(service.getPersons());
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getPerson(@PathVariable String username) {
        try {
            Person person = service.getPerson(username);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No person found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createPerson(@Valid @RequestBody PersonDto personDto, BindingResult br) {
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            service.createPerson(personDto);
            return new ResponseEntity<>("Person created", HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "{username}")
    public ResponseEntity<Object> deletePerson(@PathVariable String username) {
        try {
            service.deletePerson(username);
            return new ResponseEntity<>("Person removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Person does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{username}/image")
    public String uploadImage(@PathVariable String username, @RequestBody MultipartFile picture) {
        Person person = service.getPerson(username);
        if (ObjectUtils.isEmpty(person)) {
            throw new UsernameNotFoundException(username);
        }
        try {
            person.setPicture(picture.getBytes());
            Boolean success = service.updatePerson(username, person);
            if (success) {
                return "Image uploaded for " + username;
            } else {
                return "Something went wrong";
            }
        } catch (IOException e) {
            throw new BadRequestException("Error while uploading image");
        }
    }

    @GetMapping(value = "/{username}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    byte[] getImage(@PathVariable String username) {
        Person person = service.getPerson(username);
        if (ObjectUtils.isEmpty(person)) {
            throw new UsernameNotFoundException(username);
        }
        return person.getPicture();
    }

    @DeleteMapping(value = "/{username}/image")
    public ResponseEntity<Object> deleteImage(@PathVariable String username) {
        if (service.deleteImage(username)) {
            return new ResponseEntity<>("Image deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/{username}/password")
    public ResponseEntity<Object> setPassword(@PathVariable String username, @RequestBody String password) {
        service.setPassword(username, password);
        return new ResponseEntity<>("Password updated", HttpStatus.OK);
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<Object> updatePerson(@PathVariable String username, @RequestBody Person person) {
        service.updatePerson(username, person);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(service.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            service.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new BadRequestException("Cannot create authority. " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        try {
            service.removeAuthority(username, authority);
            return new ResponseEntity<>("Authority removed", HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Cannot delete authority. " + e.getMessage());
        }
    }
}
