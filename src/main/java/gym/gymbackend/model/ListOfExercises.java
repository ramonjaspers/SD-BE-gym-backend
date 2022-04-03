package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise_list")
public class ListOfExercises {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // One workout has one list of exercises
    @OneToOne(mappedBy = "workout")
    @JsonIgnore
    Workout workout;

    //One list of exercises contains one or many exercises
    @OneToMany(mappedBy = "exercise")
    @JsonIgnore
    List<Exercise> exercises;

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

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
