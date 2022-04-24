package gym.gymbackend.dto;

import gym.gymbackend.model.Person;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class EmployeeDto {
    @NotEmpty
    @Size(max = 128)
    private final Long id;

    @Max(value = 10000, message = "Salary too high, check the salary value or contact the administrator")
    private final Integer salary;

    @Past(message = "Date must be in the past!")
    private final Date dateOfEmployment;

    @Past(message = "Date must be in the past!")
    private final Date dateOfBirth;

    @NotEmpty
    private final String func;

    private final Integer workweekDuration;

    private final Person person;
}
