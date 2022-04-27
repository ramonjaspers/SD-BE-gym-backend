package gym.gymbackend.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Handles the composite key for the plannedActivity entity
 */
public class PlannedActivityKey implements Serializable {
    @NotNull
    private Long workout;
    @NotBlank
    private String activity;
}
