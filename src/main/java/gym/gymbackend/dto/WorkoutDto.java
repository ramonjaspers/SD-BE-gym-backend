package gym.gymbackend.dto;

import gym.gymbackend.model.Activity;
import gym.gymbackend.model.Person;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class WorkoutDto {
    private final Person person;

    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotEmpty
    List<String> activities;
}
