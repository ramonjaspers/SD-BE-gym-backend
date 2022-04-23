package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RoleDto {
    @NotEmpty
    @Size(max = 128)
    private final Long id;

    @NotBlank
    @Size(max = 128)
    private final String name;
}
