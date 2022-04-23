package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public List<Person> getPersons();
    public Optional<Person> getPerson(String username);
    public String createPerson(PersonDto personDto);
    public Boolean deletePerson(String username);
    public Boolean updatePerson(String username, Person person);
    public Boolean deleteImage(String username);
}
