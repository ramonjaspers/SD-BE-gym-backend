package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.model.Employee;
import gym.gymbackend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeImplementation implements EmployeeService {

    private final EmployeeRepository repos;

    public EmployeeImplementation(EmployeeRepository repos) {
        this.repos = repos;
    }

    @Override
    public EmployeeDto getEmployee(String username) {
        return null;
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        return null;
    }


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public Boolean deleteEmployee(String username) {
        return null;
    }

    @Override
    public Boolean updateEmployee(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public Boolean updateSalary(EmployeeDto employeeDto) {
        return null;
    }
}
