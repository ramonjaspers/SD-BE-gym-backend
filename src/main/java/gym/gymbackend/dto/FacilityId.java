package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class FacilityId {

    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotBlank
    private Long minimumSubscriptionId;
}
