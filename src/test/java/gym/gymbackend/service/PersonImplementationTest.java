package gym.gymbackend.service;

import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.enums.Sex;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.model.Authority;
import gym.gymbackend.model.Person;
import gym.gymbackend.repository.EmployeeRepository;
import gym.gymbackend.repository.PersonRepository;
import gym.gymbackend.repository.SubscriptionRepository;
import gym.gymbackend.repository.WorkoutRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonImplementationTest {


    @InjectMocks
    @Autowired
    PersonImplementation implementation;

    @Mock
    private PersonRepository personRepository;

    @Mock
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Mock
    @Autowired
    EmployeeRepository employeeRepository;

    @Mock
    @Autowired
    WorkoutRepository workoutRepository;

    @Mock
    @Autowired
    PasswordEncoder passwordEncoder;



    private Person testPerson;
    private PersonDto testPersonDto;
    private String testUserName = "henk";
    private String testPassword = "password";
    private String testName = "Henk jam";
    private String testRole = "ROLE_PERSON";
    private String testAddress = "straat 44";
    private Date testDateOfBirth = new Date(2022, Calendar.FEBRUARY, 22);
    private Long testCredit = 233L;
    private Sex testSex = Sex.MALE;
    private String testBankBumber = "242k4324n34j";
    private String testEmail = "test@test.nl";

    Person createPersonObject(String username, String password, String name, String address, Date dateOfBirth, Long credit, Sex sex, String email, String banknumber) {
        Person person = new Person();
        person.setUsername(username);
        person.setPassword(passwordEncoder.encode(password));
        person.setName(name);
        person.setAddress(address);
        person.setDateOfBirth(dateOfBirth);
        person.setCredit(credit);
        person.setSex(sex);
        person.setEmail(email);
        person.setBankNumber(banknumber);
        person.setEnabled(true);
        return person;
    }

    PersonDto createPersonDtoObject(String username, String password, String name, String address, Date dateOfBirth, Long credit, Sex sex, String email, String banknumber) {
        Set<String> auths = new HashSet<String> ();
        auths.add("ROLE_ADMIN");
        PersonDto personDto = new PersonDto();
        personDto.setUsername(username);
        personDto.setPassword(password);
        personDto.setName(name);
        personDto.setAddress(address);
        personDto.setDateOfBirth(dateOfBirth);
        personDto.setCredit(credit);
        personDto.setSex(sex);
        personDto.setEmail(email);
        personDto.setBankNumber(banknumber);
        personDto.setAuthorities(auths);
        return personDto;
    }

    @BeforeEach
    void setUp() {
        testPerson = createPersonObject(testUserName, testPassword, testName, testAddress, testDateOfBirth, testCredit, testSex, testEmail, testBankBumber);
        testPersonDto = createPersonDtoObject(testUserName, testPassword, testName, testAddress, testDateOfBirth, testCredit, testSex, testEmail, testBankBumber);
        Authority personAuthority = new Authority(testUserName, "ROLE_PERSON");
        Authority adminAuthority = new Authority(testUserName, "ROLE_ADMIN");
        testPerson.addAuthority(personAuthority);
        testPerson.addAuthority(adminAuthority);
    }


    @Test
    void personExists() {
        Mockito.when(personRepository.findById(testUserName)).thenReturn(java.util.Optional.ofNullable(testPerson));
        boolean result = implementation.personExists(testUserName);
        Assertions.assertTrue(result);
    }

    @Test
    void getPersons() {
        List<Person> persons = new ArrayList<>();

        Mockito.when(personRepository.findAll()).thenReturn(persons);
        List<Person> result = implementation.getPersons();
        Assertions.assertEquals(result, persons);
    }

    @Test
    void getPerson() {
        Mockito.when(personRepository.findById(testUserName)).thenReturn(java.util.Optional.ofNullable(testPerson));
        Person result = implementation.getPerson(testUserName);
        Assertions.assertEquals(result, testPerson);
    }

    @Test
    void createPerson() {
        Throwable exception = assertThrows(BadRequestException.class, () -> implementation.createPerson(testPersonDto));
        assertEquals("Cannot create person.", exception.getMessage());
    }

    @Test
    void deletePerson() {
        Mockito.when(personRepository.findById(testUserName)).thenReturn(java.util.Optional.ofNullable(testPerson));
        implementation.deletePerson(testUserName);
    }

    @Test
    void updatePerson() {
        Mockito.when(personRepository.findById(testUserName)).thenReturn(java.util.Optional.ofNullable(testPerson));
        boolean result = implementation.updatePerson(testUserName, testPerson);
        Assertions.assertTrue(result);
    }


    @Test
    void getAuthorities() {
        Mockito.when(personRepository.findById(testUserName)).thenReturn(java.util.Optional.ofNullable(testPerson));
        Set<Authority> auths = implementation.getAuthorities(testUserName);
        Set<Authority> emptySet = new HashSet<>();
        assertNotEquals(emptySet, auths);
    }

    @Test
    void addAuthority() {
        Mockito.when(personRepository.findById(testUserName)).thenReturn(java.util.Optional.ofNullable(testPerson));
        implementation.addAuthority(testUserName, "ROLE_EMPLOYEE");
        Set<Authority> auths = testPerson.getAuthorities();
        assertEquals(3, auths.size());
    }

    @Test
    void removeAuthority() {
        Mockito.when(personRepository.findById(testUserName)).thenReturn(java.util.Optional.ofNullable(testPerson));
        implementation.removeAuthority(testUserName, "ROLE_PERSON");
        Set<Authority> auths = testPerson.getAuthorities();
        assertEquals(1, auths.size());
    }

    @Test
    void deleteImage() {
        Mockito.when(personRepository.findById(testUserName)).thenReturn(java.util.Optional.ofNullable(testPerson));
        implementation.deleteImage(testUserName);
        byte[] image = testPerson.getPicture();
        assertNull(image);
    }
}
