package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class WorkoutDto {
    @NotEmpty
    @Size(max = 128)
    private final Long id;

    @NotEmpty
    @Size(max = 128)
    private final Long memberId;

    @NotBlank
    @Size(max = 128)
    private final String name;

}
