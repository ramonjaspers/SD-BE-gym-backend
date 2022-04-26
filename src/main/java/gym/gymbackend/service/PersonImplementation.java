package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.InvalidPasswordException;
import gym.gymbackend.exceptions.NotAuthorizedException;
import gym.gymbackend.exceptions.PersonNotFoundException;
import gym.gymbackend.model.Authority;
import gym.gymbackend.model.Person;
import gym.gymbackend.repository.PersonRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PersonImplementation implements PersonService {

    PasswordEncoder passwordEncoder;
    private final PersonRepository repos;

    public PersonImplementation(PersonRepository repos, PasswordEncoder passwordEncoder) {
        this.repos = repos;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param username
     * @return {Boolean} isPresent()
     */
    public boolean personExists(String username) {
        return repos.findById(username).isPresent();
    }

    @Override
    public List<Person> getPersons() {
        return repos.findAll();
    }

    @Override
    public Person getPerson(String username) {
        if (!personExists(username)) {
            throw new PersonNotFoundException();
        }
        return repos.findById(username).get();
    }

    public String createPerson(PersonDto personDto) {
        try {
            Person person = new Person();
            person.setUsername(personDto.getUsername());
            person.setPassword(passwordEncoder.encode(personDto.getPassword()));
            person.setName(personDto.getName());
            person.setAddress(personDto.getAddress());
            person.setDateOfBirth(personDto.getDateOfBirth());
            person.setCredit(personDto.getCredit());
            person.setSex(personDto.getSex());
            person.setEmail(personDto.getEmail());
            person.setBankNumber(personDto.getBankNumber());
            person.setEnabled(true);
            // Set default minimum authority
            person.addAuthority("ROLE_PERSON");
            for (String s : personDto.getAuthorities()) {
                if (Objects.equals(s, "ROLE_PERSON")) {
                    continue;
                }
                if (!s.startsWith("ROLE_")) {
                    s = "ROLE_" + s;
                }
                person.addAuthority(s);
            }
            Person newPerson = this.repos.save(person);
            return newPerson.getUsername();
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }
    }

    public Boolean deletePerson(String username) {
        if (personExists(username)) {
            try {
                repos.deleteById(username);
            } catch (Exception ex) {
                throw new BadRequestException();
            }
            return true;
        } else {
            throw new PersonNotFoundException(username);
        }
    }

    @Override
    public Boolean updatePerson(String username, Person newPerson) {
        if (!personExists(username)) {
            throw new PersonNotFoundException(username);
        }
        Person person = repos.findById(username).get();
        person.setPassword(passwordEncoder.encode(newPerson.getPassword()));
        person.setPicture(newPerson.getPicture());
        person.setAddress(newPerson.getAddress());
        person.setBankNumber(newPerson.getBankNumber());
        person.setEmail(newPerson.getEmail());
        person.setEnabled(newPerson.isEnabled());
        person.setSex(newPerson.getSex());
        person.setName(newPerson.getName());
        person.setDateOfBirth(newPerson.getDateOfBirth());
        person.setCredit(newPerson.getCredit());
        repos.save(person);
        return true;
    }

    public Set<Authority> getAuthorities(String username) {
        if (!personExists(username)) {
            throw new PersonNotFoundException(username);
        }
        Person person = repos.findById(username).get();
        return person.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        if (!personExists(username)) {
            throw new PersonNotFoundException(username);
        }
        Person person = this.repos.findById(username).get();
        person.addAuthority(new Authority(username, authority));
        this.repos.save(person);
    }

    public void removeAuthority(String username, String authority) {
        if (!personExists(username)) {
            throw new PersonNotFoundException(username);
        }
        Person person = this.repos.findById(username).get();
        person.removeAuthority(authority);
        this.repos.save(person);
    }

    @Override
    public Boolean deleteImage(String username) {
        if (!personExists(username)) {
            throw new PersonNotFoundException(username);
        }
        Person person = repos.findById(username).get();
        person.setPicture(null);
        this.repos.save(person);
        return true;
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    public void setPassword(String username, String password) {
        if (username.equals(getCurrentUserName())) {
            if (password.length() > 2) {
                if (personExists(username)) {
                    Person person = repos.findById(username).get();
                    person.setPassword(passwordEncoder.encode(password));
                    repos.save(person);
                } else {
                    throw new PersonNotFoundException(username);
                }
            } else {
                throw new InvalidPasswordException();
            }
        } else {
            throw new NotAuthorizedException();
        }
    }
}
