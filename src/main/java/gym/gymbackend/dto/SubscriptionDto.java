package gym.gymbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SubscriptionDto {
    @NotNull
    private final Date subscriptionStartDate;

    @NotNull
    private final Date subscriptionEndDate;

    @NotBlank
    private final String membership;
}
