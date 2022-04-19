package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // Multiple workouts can belong to a single person
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    @JsonIgnore
    Person person;

    @OneToMany(mappedBy="id")
    List<PlannedExercise> plannedExerciseList = new ArrayList<>();

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
