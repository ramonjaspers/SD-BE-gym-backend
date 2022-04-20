package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gym.gymbackend.enums.Muscle;

import javax.persistence.*;

@Entity
public class ExerciseMuscle {
    @Id
    @GeneratedValue
    private Long id;
    private Muscle muscle;

    // Multiple muscles can belong to a single exercise
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonIgnore
    Exercise exercise;

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Muscle getMuscle() {
        return muscle;
    }

    public void setMuscle(Muscle muscle) {
        this.muscle = muscle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
