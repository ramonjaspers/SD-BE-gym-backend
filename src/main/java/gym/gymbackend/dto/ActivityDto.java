package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ActivityDto {
    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotNull
    private final Long facilityId;
}
