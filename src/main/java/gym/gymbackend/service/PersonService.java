package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.model.Authority;
import gym.gymbackend.model.Person;

import java.util.List;
import java.util.Set;

public interface PersonService {
    /**
     * get persons
     *
     * @return {@link List}
     */
    List<Person> getPersons();

    /**
     * get personby username
     *
     * @param username username
     * @return {@link Person}
     */
    Person getPerson(String username);

    /**
     * create person
     *
     * @param personDto personDto
     * @return {@link String}
     */
    String createPerson(PersonDto personDto);

    /**
     * delete person by username
     *
     * @param username username
     * @return {@link Boolean}
     */
    Boolean deletePerson(String username);

    /**
     * update person by username
     *
     * @param username username
     * @param person   person
     * @return {@link Boolean}
     */
    Boolean updatePerson(String username, Person person);

    /**
     * delete image by username
     *
     * @param username username
     * @return {@link Boolean}
     */
    Boolean deleteImage(String username);

    /**
     * set password by username
     *
     * @param username username
     * @param Password password
     */
    void setPassword(String username, String Password);

    /**
     * get authorities by username
     *
     * @param username username
     * @return {@link Set}
     */
    Set<Authority> getAuthorities(String username);

    /**
     * add authority by username
     *
     * @param username      username
     * @param authorityName authorityName
     */
    void addAuthority(String username, String authorityName);

    /**
     * remove authority by username
     *
     * @param username  username
     * @param authority authority
     */
    void removeAuthority(String username, String authority);
}
