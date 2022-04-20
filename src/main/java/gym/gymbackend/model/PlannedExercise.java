package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PlannedExercise {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // One or many planned exercises have one workout
    @ManyToOne
    @JoinColumn(name="workout_id", nullable=false)
    @JsonIgnore
    Workout workout;

    // One or many planned exercise contains one exercise
    @ManyToOne
    @JoinColumn(name="exercise_id", nullable=false)
    Exercise exercise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercises) {
        this.exercise = exercises;
    }
}
