package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Workout {
    // Multiple workouts can belong to a single person
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    @JsonIgnore
    Person person;
    // one workout has one or many planned activities
    @OneToMany(mappedBy = "workout")
    List<PlannedActivity> plannedActivities;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

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

    public List<PlannedActivity> getPlannedActivities() {
        return plannedActivities;
    }

    public void setPlannedActivities(List<PlannedActivity> plannedActivities) {
        this.plannedActivities = plannedActivities;
    }
}
