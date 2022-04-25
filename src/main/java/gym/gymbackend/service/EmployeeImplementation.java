package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.PersonNotFoundException;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.model.Employee;
import gym.gymbackend.model.Person;
import gym.gymbackend.repository.EmployeeRepository;
import gym.gymbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository repos;
    @Autowired
    private PersonRepository personRepository;

    public boolean employeeExists(String username) {
        return repos.findById(username).isPresent();
    }

    @Override
    public Employee getEmployee(String username) {
        if (!employeeExists(username)) {
            throw new PersonNotFoundException();
        }

        return repos.findById(username).get();
    }

    @Override
    public List<Employee> getEmployees() {
        return repos.findAll();
    }

    @Override
    public void createEmployee(String username, EmployeeDto employeeDto) {
        try {
            if (username.isEmpty() || !personRepository.existsById(username)) {
                throw new RecordNotFoundException("Invalid data received");
            }
            Employee employee = new Employee();
            Optional<Person> personOptional = personRepository.findById(username);
            if (personOptional.isEmpty()) {
                throw new PersonNotFoundException(username);
            }
            employee.setDateOfEmployment(employeeDto.getDateOfEmployment());
            employee.setDateTillEmployment(employeeDto.getDateTillEmployment());
            employee.setFunc(employeeDto.getFunc());
            employee.setSalary(employeeDto.getSalary());
            employee.setWorkWeekDuration(employeeDto.getWorkweekDuration());
            employee.setPerson(personOptional.get());
            repos.save(employee);
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create employee. " + ex.getMessage());
        }
    }

    @Override
    public void deleteEmployee(String username) {
        if (employeeExists(username)) {
            repos.deleteById(username);
        } else {
            throw new PersonNotFoundException(username);
        }
    }

    @Override
    public void updateEmployee(String username, EmployeeDto employeeDto) {
        if (!employeeExists(username)) {
            throw new PersonNotFoundException(username);
        }
        Employee employee = repos.findById(username).get();
        employee.setDateOfEmployment(employeeDto.getDateOfEmployment());
        employee.setDateTillEmployment(employeeDto.getDateTillEmployment());
        employee.setFunc(employeeDto.getFunc());
        employee.setSalary(employeeDto.getSalary());
        employee.setWorkWeekDuration(employeeDto.getWorkweekDuration());
        repos.save(employee);
    }

    @Override
    public void updateSalary(String username, Long salary) {
        if (!employeeExists(username)) {
            throw new PersonNotFoundException(username);
        }
        Employee employee = repos.findById(username).get();
        employee.setSalary(salary);
        repos.save(employee);
    }
}
