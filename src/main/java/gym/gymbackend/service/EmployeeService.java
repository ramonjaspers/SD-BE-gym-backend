package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(String username);

    List<Employee> getEmployees();

    void createEmployee(String username, EmployeeDto employeeDto);

    void deleteEmployee(String username);

    void updateEmployee(String username, EmployeeDto employeeDto);

    void updateSalary(String username, Long salary);
}
