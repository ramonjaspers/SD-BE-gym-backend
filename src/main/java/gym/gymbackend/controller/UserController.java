package gym.gymbackend.controller;

import gym.gymbackend.dto.EmployeeDto;
import gym.gymbackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final EmployeeService service;

    public UserController(EmployeeService service){
        this.service = service;
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<Object> getEmployees() {
        List<EmployeeDto> employees = service.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Object> getEmployee(@PathVariable String id) {
        try{
            EmployeeDto employee = service.getEmployee(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Error e) {
            return new ResponseEntity<>("No person found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "/employees")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeDto employee, BindingResult br) {
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        service.createEmployee(employee);
        return new ResponseEntity<>("Employee created", HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String user) {
        try {
            service.getEmployee(user);
            service.deleteEmployee(user);
            return new ResponseEntity<>("Person removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Person does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
