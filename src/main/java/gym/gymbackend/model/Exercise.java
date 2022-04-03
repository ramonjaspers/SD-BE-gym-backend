package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // An exercise list has multiple exercises
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonIgnore
    ListOfExercises exerciseList;

    // Exercises have a single facility
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonIgnore
    Facility facility;

    //An exercise can have multiple muscles
    @OneToMany(mappedBy = "exerciseMuscle")
    @JsonIgnore
    List<ExerciseMuscle> muscles = new ArrayList<>();

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

    public ListOfExercises getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(ListOfExercises exerciseList) {
        this.exerciseList = exerciseList;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public List<ExerciseMuscle> getMuscles() {
        return muscles;
    }

    public void setMuscles(List<ExerciseMuscle> muscles) {
        this.muscles = muscles;
    }
}
