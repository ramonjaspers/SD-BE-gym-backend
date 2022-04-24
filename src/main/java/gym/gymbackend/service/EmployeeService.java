package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployee(String username);
    List<EmployeeDto> getEmployees();
    void createEmployee(EmployeeDto employeeDto);
    Boolean deleteEmployee(String username);
    Boolean updateEmployee(EmployeeDto employeeDto);
    Boolean updateSalary(EmployeeDto employeeDto);
}
