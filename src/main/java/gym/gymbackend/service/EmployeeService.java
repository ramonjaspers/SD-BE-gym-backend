package gym.gymbackend.service;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.model.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * get employee by username
     *
     * @param username username
     * @return {@link Employee}
     * @see Employee
     */
    Employee getEmployee(String username);

    /**
     * get employees
     *
     * @return {@link List}
     * @see List
     * @see Employee
     */
    List<Employee> getEmployees();

    /**
     * create employee by username
     *
     * @param username    username
     * @param employeeDto employeeDto
     */
    void createEmployee(String username, EmployeeDto employeeDto);

    /**
     * delete employee by username
     *
     * @param username username
     */
    void deleteEmployee(String username);

    /**
     * update employee by username
     *
     * @param username    username
     * @param employeeDto employeeDto
     */
    void updateEmployee(String username, EmployeeDto employeeDto);

    /**
     * update salary by username
     *
     * @param username username
     * @param salary   salary
     */
    void updateSalary(String username, Long salary);
}
