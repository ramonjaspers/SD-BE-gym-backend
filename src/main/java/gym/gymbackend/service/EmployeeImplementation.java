package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.model.Employment;
import gym.gymbackend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImplementation implements EmployeeService {

    private final EmployeeRepository repos;

    public EmployeeImplementation(EmployeeRepository repos) {
        this.repos = repos;
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employment> repoEmployees = this.repos.findAll();
        List<EmployeeDto> employees = new ArrayList<>();

        repoEmployees.forEach(employee -> employees.add(
                new EmployeeDto(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getSalary(),
                        employee.getDateOfBirth(),
                        employee.getDateOfEmployment(),
                        employee.getTimeStamp()
                )));
        return employees;
    }

    @Override
    public EmployeeDto getEmployee(String employee) {
        Employment repoEmployee = this.repos.getById(employee);

        return new EmployeeDto(
                repoEmployee.getFirstName(),
                repoEmployee.getLastName(),
                repoEmployee.getEmail(),
                repoEmployee.getSalary(),
                repoEmployee.getDateOfBirth(),
                repoEmployee.getDateOfEmployment(),
                repoEmployee.getTimeStamp()
        );
    }

    @Override
    public Employment createEmployee(EmployeeDto EmployeeDto) {
        Employment employee = new Employment();
        employee.setFirstName(EmployeeDto.getFirstName());
        employee.setLastName(EmployeeDto.getLastName());
        employee.setEmail(EmployeeDto.getEmail());
        employee.setSalary(EmployeeDto.getSalary());
        employee.setDateOfBirth(EmployeeDto.getDateOfBirth());
        employee.setDateOfEmployment(EmployeeDto.getDateOfEmployment());
        employee.setTimeStamp(EmployeeDto.getTimeStamp());
        return this.repos.save(employee);
    }

    @Override
    public Boolean deleteEmployee(String userName) {
        return true;
    }

    @Override
    public Employment updateEmployee(EmployeeDto employeeDto) {
        Employment employee = new Employment();
        return employee;
    }

}
