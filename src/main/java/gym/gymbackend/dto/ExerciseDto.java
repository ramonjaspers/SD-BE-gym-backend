package gym.gymbackend.dto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ExerciseDto {

    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotEmpty
    private final Long facilityId;

    @NotEmpty
    private final Long exerciseMuscleId;
}
