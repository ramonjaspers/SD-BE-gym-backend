package gym.gymbackend.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SubscriptionDto {
    @NotEmpty
    @Size(max = 128)
    private final Long id;

    @NotBlank
    @Size(max = 128)
    private final String name;

    @NotEmpty
    private final Float price;

    @NotEmpty
    private final Long subscriptionId;
}
