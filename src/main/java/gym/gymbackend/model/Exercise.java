package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // Exercises have a single facility
    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    @JsonIgnore
    Facility facility;

    //An exercise can have multiple muscles
    @OneToMany(mappedBy = "exercise")
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
