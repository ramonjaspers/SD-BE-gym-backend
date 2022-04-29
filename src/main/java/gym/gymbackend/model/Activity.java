package gym.gymbackend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Activity {
    // Exercises can have one facility
    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    Facility facility;
    // Multiple Exercises can have multiple muscles/ mutliple muscles can have multiple exercises
    @OneToMany(mappedBy = "activity")
    List<ExerciseMuscle> muscles;
    @Id
    private String name;

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

    public String getMuscles() {
        StringBuilder sb = new StringBuilder();
        for (ExerciseMuscle em: muscles) {
            if (sb.length() > 0 ){
               sb.append(", ");
            }
            sb.append(em.getMuscle());
        }
        return sb.toString();
    }
}
