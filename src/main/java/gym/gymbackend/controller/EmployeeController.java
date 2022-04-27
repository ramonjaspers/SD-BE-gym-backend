package gym.gymbackend.controller;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.model.Employee;
import gym.gymbackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = service.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getEmployee(@PathVariable String username) {
        try {
            Employee employee = service.getEmployee(username);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No employee found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{username}")
    public ResponseEntity<Object> createEmployee(@PathVariable String username, @Valid @RequestBody EmployeeDto employee, BindingResult br) {
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            service.createEmployee(username, employee);
            return new ResponseEntity<>("Employee created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String username) {
        try {
            service.deleteEmployee(username);
            return new ResponseEntity<>(username + " removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Employee does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<Object> updateEmployee(@PathVariable String username, @Valid @RequestBody EmployeeDto employeeDto) {
        service.updateEmployee(username, employeeDto);
        return new ResponseEntity<>(username + " employee fields updated", HttpStatus.OK);
    }

    @PatchMapping(value = "/{username}/salary")
    public ResponseEntity<Object> updateSalary(@PathVariable("username") String username, @RequestBody Long salary) {
        service.updateSalary(username, salary);
        return new ResponseEntity<>(username + " salary updated", HttpStatus.OK);
    }
}
