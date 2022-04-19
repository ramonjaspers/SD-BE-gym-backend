package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.model.Person;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface PersonService {

    public List<PersonDto> getPersons();
    public PersonDto getPerson(String username);
    public String createPerson(PersonDto personDto);
    public void deletePerson(String username);
    public void updatePerson(String username, PersonDto personDto);
}
