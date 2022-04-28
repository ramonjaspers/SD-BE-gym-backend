package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@IdClass(PlannedActivityKey.class)
public class PlannedActivity {
    // One or many planned exercises have one workout
    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    @JsonIgnore
    @Id
    Workout workout;
    // One or many planned activity contains one activity
    @ManyToOne
    @JoinColumn(name = "activity", nullable = false)
    @Id
    Activity activity;

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity exercise) {
        this.activity = exercise;
    }
}
