package gym.gymbackend.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SubscriptionDto {
    @NotBlank
    @Size(max = 128)
    private final Long id;

    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotBlank
    private final Float price;

    @NotBlank
    private final Long subscriptionId;
}
