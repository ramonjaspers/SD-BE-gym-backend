package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getPersons();
    Person getPerson(String username);
    String createPerson(PersonDto personDto);
    Boolean deletePerson(String username);
    Boolean updatePerson(String username, Person person);
    Boolean deleteImage(String username);
    void setPassword(String username, String Password);
}
