package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.model.Authority;
import gym.gymbackend.model.Person;

import java.util.List;
import java.util.Set;

public interface PersonService {

    List<Person> getPersons();
    Person getPerson(String username);
    String createPerson(PersonDto personDto);
    Boolean deletePerson(String username);
    Boolean updatePerson(String username, Person person);
    Boolean deleteImage(String username);
    void setPassword(String username, String Password);
    Set<Authority> getAuthorities(String username);
    void addAuthority(String username, String authorityName);
    void removeAuthority(String username, String authority);
}
