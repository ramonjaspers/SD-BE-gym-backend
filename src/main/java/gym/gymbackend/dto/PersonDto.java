package gym.gymbackend.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PersonDto {
    private Long id;
    @NotEmpty
    @Size(max = 128)
    private final String name;

    @NotEmpty
    @Size(max = 128)
    private final String username;

    @NotEmpty
    @Size(max = 128)
    private final String address;

    @Past(message = "Date must be in the past!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final Date dateOfBirth;

    @Max(value = 10000, message = "Salary too high, check the salary value or contact the administrator")
    private final int salary;

    @Past(message = "Date must be in the past!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final Date dateOfEmployment;

    @Past(message = "Date must be in the past!")
    // @JsonFormat(pattern="dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final Date dateOfBirth;

    private final LocalDateTime timeStamp;
}
