package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class EmployeeDto {
    @Max(value = 10000, message = "Salary too high, check the salary value or contact the administrator")
    private final Long salary;

    @Past(message = "dateOfEmployment must be in the past")
    private final Date dateOfEmployment;

    @FutureOrPresent(message = "dateTillEmployment must be in the future")
    private final Date dateTillEmployment;

    @NotBlank(message = "Employee needs a 'func'(function)")
    private final String func;

    @NotNull(message = "Employee needs a workweekDuration")
    private final Integer workweekDuration;
}
