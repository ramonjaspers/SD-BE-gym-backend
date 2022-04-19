package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDto> getEmployees();
    public EmployeeDto getEmployee(String username);
    public EmployeeDto createEmployee(EmployeeDto employeeDto);
    public Boolean deleteEmployee(String username);
    public Boolean updateEmployee(EmployeeDto employeeDto);
}
