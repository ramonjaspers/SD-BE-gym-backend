package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "facility")
public class Workout {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // Multiple workouts can belong to a single person
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonIgnore
    Person person;

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
