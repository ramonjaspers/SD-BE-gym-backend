package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.exceptions.PersonNotFoundException;
import gym.gymbackend.model.Authority;
import gym.gymbackend.model.Person;
import gym.gymbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonImplementation implements PersonService {

    private final PersonRepository repos;

    @Autowired
    PasswordEncoder passwordEncoder;

    public PersonImplementation(PersonRepository repos) {
        this.repos = repos;
    }

    public boolean personExists(String username) {
        return repos.existsById(username);
    }

    @Override
    public List<Person> getPersons() {
        return repos.findAll();
    }

    @Override
    public Optional<Person> getPerson(String username) {
        if (personExists(username)) {
            return repos.findById(username);
        }
        throw new PersonNotFoundException();
    }


    public String createPerson(PersonDto personDto) {
        try {
            Person person = new Person();
            person.setUsername(personDto.getUsername());
            person.setPassword(passwordEncoder.encode(personDto.getPassword()));
            person.setEmail(personDto.getEmail());
            person.addAuthority("ROLE_USER");
            for (String s : personDto.getAuthorities()) {
                if (!Objects.equals(s, "ROLE_USER")) {
                    if (!s.startsWith("ROLE_")) {
                        s = "ROLE_" + s;
                    }
                    person.addAuthority(s);
                }
            }
            Person newPerson = repos.save(person);
            return newPerson.getUsername();
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }
    }

    public Boolean deletePerson(String username) {
        if (personExists(username)) {
            repos.deleteById(username);
            return true;
        }
        else {
            throw new PersonNotFoundException(username);
        }
    }

    @Override
    public Boolean updatePerson(String username, PersonDto personDto) {
        if (!personExists(username)) {
            throw new PersonNotFoundException();
        }
        Person person = this.repos.findById(personDto.getUsername()).get();
        person.setPassword(personDto.getPassword());
        this.repos.save(person);
        return true;
    }

    public Set<Authority> getAuthorities(String username) {
        if (!repos.existsById(username)) {
            throw new PersonNotFoundException(username);
        }
        Person person = this.repos.findById(username).get();
        return person.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        if (!this.repos.existsById(username)) {
            throw new PersonNotFoundException(username);
        }
        Person person = this.repos.findById(username).get();
        person.addAuthority(new Authority(username, authority));
        this.repos.save(person);
    }

    public void removeAuthority(String username, String authority) {
        if (!this.repos.existsById(username)) throw new PersonNotFoundException(username);
        Person person = this.repos.findById(username).get();
        Authority authorityToRemove = person.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        person.removeAuthority(authorityToRemove);
        this.repos.save(person);
    }

    /**
     * Turn a PersonDto object into a Person
     *
     * @param personDto PersonDto
     * @return Person
     */
    public Person dtoToPerson(PersonDto personDto) {
        Person Person = new Person();
        Person.setName(personDto.getName());
        Person.setUsername(personDto.getUsername());
        Person.setPassword(personDto.getPassword());
        Person.setApiKey(personDto.getApiKey());
        Person.setAddress(personDto.getAddress());
        Person.setDateOfBirth(personDto.getDateOfBirth());
        Person.setCredit(personDto.getCredit());
        Person.setSex(personDto.getSex());
        Person.setBankNumber(personDto.getBankNumber());
        Person.setSubscription(personDto.getSubscription());
        Person.setPicture(personDto.getPicture());
        return Person;
    }
}
