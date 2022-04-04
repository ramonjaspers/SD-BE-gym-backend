package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class WorkoutDto {
    @NotBlank
    @Size(max = 128)
    private final Long id;

    @NotBlank
    @Size(max = 128)
    private final Long memberId;

    @NotBlank
    @Size(max = 128)
    private final String name;

}
