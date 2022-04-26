package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class WorkoutDto {
    @NotNull
    @Size(max = 128)
    private final Long id;

    @NotNull
    @Size(max = 128)
    private final Long memberId;

    @NotBlank
    @Size(max = 128)
    private final String name;

}
