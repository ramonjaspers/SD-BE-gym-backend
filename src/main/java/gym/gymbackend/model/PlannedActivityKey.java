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

    public Long getWorkout() {
        return workout;
    }

    public void setWorkout(Long workout) {
        this.workout = workout;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
