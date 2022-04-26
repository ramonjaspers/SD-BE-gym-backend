package gym.gymbackend.dto;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ActivityDto {
    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotNull
    private final Long facilityId;
}
