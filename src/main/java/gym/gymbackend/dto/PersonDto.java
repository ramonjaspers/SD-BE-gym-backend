package gym.gymbackend.dto;

import gym.gymbackend.enums.Sex;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class PersonDto {
    @NotBlank
    @Size(max = 128)
    private final Long id;

    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotBlank
    @Size(max = 128)
    private final String username;

    @NotBlank
    @Size(max = 128)
    private final String address;

    @NotBlank
    @Past(message = "Date must be in the past!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final Date dateOfBirth;

    @Max(value = 5000, message = "Credit too high, check the credit value or contact the administrator")
    private final Float credit;

    @Past(message = "Date must be in the past!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final Date dateOfEmployment;

    @NotBlank
    private final Sex sex;


    @NotBlank
    @Past(message = "Date must be in the past!")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final String bankNumber;

    @NotBlank
    private final Long roleId;

    private final Long subscriptionId;

    private final String picture;
}
