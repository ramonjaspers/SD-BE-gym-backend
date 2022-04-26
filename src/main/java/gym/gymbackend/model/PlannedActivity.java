package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PlannedActivity {
    @Id
    @GeneratedValue
    private Long id;

    // One or many planned exercises have one workout
    @ManyToOne
    @JoinColumn(name="workout_id", nullable=false)
    @JsonIgnore
    Workout workout;

    // One or many planned activity contains one activity
    @ManyToOne
    @JoinColumn(name="activity", nullable=false)
    Activity exercise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Activity getExercise() {
        return exercise;
    }

    public void setExercise(Activity exercise) {
        this.exercise = exercise;
    }
}
