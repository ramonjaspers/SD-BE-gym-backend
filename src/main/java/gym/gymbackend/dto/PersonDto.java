package gym.gymbackend.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gym.gymbackend.enums.Sex;
import gym.gymbackend.model.Subscription;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Data
public class PersonDto {
    @NotBlank
    @Size(max = 128)
    public String username;
    @NotBlank
    @Size(max = 128)
    public String password;
    @NotBlank
    @Size(max = 128)
    public String name;
    @Size(max = 128)
    public String address;
    public String email;
    public String bankNumber;
    @Past(message = "Date must be in the past!")
    public Date dateOfBirth;
    @Max(value = 10000, message = "Credit too high, check the credit value or contact the administrator")
    public Long credit;
    @NotNull
    public Sex sex;
    @JsonSerialize
    public Set<String> authorities;
    public Subscription subscription;
    public byte[] picture;
}
