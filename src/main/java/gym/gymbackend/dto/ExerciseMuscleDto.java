package gym.gymbackend.dto;

import gym.gymbackend.enums.Muscle;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ExerciseMuscleDto {
    @NotNull
    private final String activity;

    @NotNull
    private final List<Muscle> muscles;
}
