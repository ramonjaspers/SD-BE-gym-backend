package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.model.Employee;

import java.util.List;

public interface EmployeeService {
    public EmployeeDto getEmployee(String username);
    public List<EmployeeDto> getEmployees();
    public EmployeeDto createEmployee(EmployeeDto employeeDto);
    public Boolean deleteEmployee(String username);
    public Boolean updateEmployee(EmployeeDto employeeDto);
    public Boolean updateSalary(EmployeeDto employeeDto);
}
