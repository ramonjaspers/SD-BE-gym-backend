package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ExerciseListDto {
    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotBlank
    @Size(max = 128)
    private final Integer memberID;

}
