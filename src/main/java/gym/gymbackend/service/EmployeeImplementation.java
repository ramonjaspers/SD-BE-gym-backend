package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.exceptions.RecordNotFoundException;
import gym.gymbackend.model.Employee;
import gym.gymbackend.model.Person;
import gym.gymbackend.repository.EmployeeRepository;
import gym.gymbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonRepository repos;


    @Override
    public EmployeeDto getEmployee(String username) {
        return null;
    }

    @Override
    public List<gym.gymbackend.dto.EmployeeDto> getEmployees() {
        return null;
    }

    @Override
    public void createEmployee(String username, EmployeeDto employeeDto) {
        try {
            if (username.isEmpty() || !personRepository.existsById(username)){
                throw new RecordNotFoundException("Invalid data recieved");
            }
            Employee employee = new Employee();
            Person person = personRepository.findById(username).get();
            employee.setDateOfEmployment(employeeDto.getDateOfEmployment());
            employee.setFunc(employeeDto.getFunc());
            employee.setSalary(employeeDto.getSalary());
            employee.setWorkWeekDuration(employeeDto.getWorkweekDuration());
            employee.setPerson(employee.getPerson());
            repos.save(employee);
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }
    }

    @Override
    public Boolean deleteEmployee(String username) {
        return null;
    }

    @Override
    public Boolean updateEmployee(gym.gymbackend.dto.EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public Boolean updateSalary(gym.gymbackend.dto.EmployeeDto employeeDto) {
        return null;
    }
}
