package gym.gymbackend.dto;

import gym.gymbackend.enums.Muscle;
import gym.gymbackend.model.ExerciseMuscle;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ExerciseDto {

    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotBlank
    private final Long facilityId;

    @NotBlank
    private final Long exerciseMuscleId;
}
