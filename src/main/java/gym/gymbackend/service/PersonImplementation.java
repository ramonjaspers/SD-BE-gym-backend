package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.InvalidPasswordException;
import gym.gymbackend.exceptions.NotAuthorizedException;
import gym.gymbackend.exceptions.PersonNotFoundException;
import gym.gymbackend.model.*;
import gym.gymbackend.repository.EmployeeRepository;
import gym.gymbackend.repository.PersonRepository;
import gym.gymbackend.repository.SubscriptionRepository;
import gym.gymbackend.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    PersonRepository repos;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    /**
     * @param username
     * @return {Boolean} isPresent()
     */
    public boolean personExists(String username) {
        return repos.findById(username).isPresent();
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
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
            Person newPerson = repos.save(person);
            return newPerson.getUsername();
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }
    }

    public void deletePerson(String username) {
        try {
            Person person = getPerson(username);
            Subscription subscription = person.getSubscription();
            Employee employee = person.getEmployee();
            List<Workout> workouts = person.getWorkouts();
            if (subscription != null) {
                subscriptionRepository.delete(subscription);
            }
            if (employee != null) {
                employeeRepository.delete(employee);
            }
            if (workouts.size() != 0) {
                workoutRepository.deleteAll(workouts);
            }
            repos.delete(person);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Boolean updatePerson(String username, Person newPerson) {
        try {
            Person person = getPerson(username);
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
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Set<Authority> getAuthorities(String username) {
        Person person = getPerson(username);
        return person.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        Person person = getPerson(username);
        person.addAuthority(new Authority(username, authority));
        repos.save(person);
    }

    public void removeAuthority(String username, String authority) {
        Person person = getPerson(username);
        person.removeAuthority(authority);
        repos.save(person);
    }

    @Override
    public Boolean deleteImage(String username) {
        Person person = getPerson(username);
        person.setPicture(null);
        repos.save(person);
        return true;
    }


    public void setPassword(String username, String password) {
        if (username.equals(getCurrentUserName())) {
            //TODO: implement password strength check
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
