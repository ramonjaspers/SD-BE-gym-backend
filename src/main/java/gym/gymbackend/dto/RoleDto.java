package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RoleDto {
    @NotNull
    @Size(max = 128)
    private final Long id;

    @NotBlank
    @Size(max = 128)
    private final String name;
}
