package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class MembershipDto {
    @NotBlank
    @Size(max = 128)
    private final String Name;

    @NotEmpty
    private final Long price;

    @NotEmpty
    private final Integer weight;
}
