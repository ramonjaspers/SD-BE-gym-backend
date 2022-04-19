package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.exceptions.UsernameNotFoundException;
import gym.gymbackend.model.Authority;
import gym.gymbackend.model.Person;
import gym.gymbackend.repository.PersonRepository;
import gym.gymbackend.utils.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PersonImplementation implements PersonService {

    private final PersonRepository repos;

    public PersonImplementation(PersonRepository repos) {
        this.repos = repos;
    }

    @Override
    public List<PersonDto> getPersons() {
        List<Person> persons = this.repos.findAll();
        List<PersonDto> dtos = new ArrayList<>();
        persons.forEach(person -> dtos.add(personToDto(person)));
        return dtos;
    }

    @Override
    public PersonDto getPerson(String username) {
        if (!this.repos.existsById(username)) {
            throw new UsernameNotFoundException(username);
        }
        Person person = this.repos.findById(username).get();

        return personToDto(person);
    }


    public String createPerson(PersonDto personDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        personDto.setApiKey(randomString);
        Person newPerson = this.repos.save(dtoToPerson(personDto));
        return newPerson.getUsername();
    }

    public void deletePerson(String username) {
        this.repos.deleteById(username);
    }

    @Override
    public void updatePerson(String username, PersonDto personDto) {
        if (!this.repos.existsById(username)) {
            throw new RecordNotFoundException();
        }
        Person person = this.repos.findById(personDto.getUsername()).get();
        person.setPassword(personDto.getPassword());
        this.repos.save(person);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!repos.existsById(username)) {
            throw new UsernameNotFoundException(username);
        }
        Person person = this.repos.findById(username).get();
        PersonDto personDto = personToDto(person);
        return personDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        if (!this.repos.existsById(username)) {
            throw new UsernameNotFoundException(username);
        }
        Person person = this.repos.findById(username).get();
        person.addAuthority(new Authority(username, authority));
        this.repos.save(person);
    }

    public void removeAuthority(String username, String authority) {
        if (!this.repos.existsById(username)) throw new UsernameNotFoundException(username);
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
        Person.setApikey(personDto.getApiKey());
        Person.setAddress(personDto.getAddress());
        Person.setDateOfBirth(personDto.getDateOfBirth());
        Person.setCredit(personDto.getCredit());
        Person.setSex(personDto.getSex());
        Person.setBankNumber(personDto.getBankNumber());
        Person.setSubscription(personDto.getSubscription());
        Person.setPicture(personDto.getPicture());
        return Person;
    }


    /**
     * Turn a person object into a PersonDto object
     *
     * @param person Person
     * @return PersonDto
     */
    public PersonDto personToDto(Person person) {
        PersonDto dto = new PersonDto();

        dto.name = person.getName();
        dto.username = person.getUsername();
        dto.password = person.getPassword();
        dto.address = person.getAddress();
        dto.bankNumber = person.getBankNumber();
        dto.dateOfBirth = person.getDateOfBirth();
        dto.credit = person.getCredit();
        dto.sex = person.getSex();
        dto.apiKey = person.getApikey();
        dto.authorities = person.getAuthorities();
        dto.subscription = person.getSubscription();
        dto.picture = person.getPicture();
        return dto;
    }
}
