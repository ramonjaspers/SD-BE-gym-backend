package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.model.Employment;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDto>getEmployees();
    public EmployeeDto getEmployee(String user);
    public Employment createEmployee(EmployeeDto employeeDto);
    public Boolean deleteEmployee(String userName);
    public Employment updateEmployee(EmployeeDto employeeDto);
}
