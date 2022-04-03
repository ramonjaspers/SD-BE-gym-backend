package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // Facilities have one subscription level
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonIgnore
    Subscription minimumSubscription;

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

    public Subscription getMinimumSubscription() {
        return minimumSubscription;
    }

    public void setMinimumSubscription(Subscription minimumSubscription) {
        this.minimumSubscription = minimumSubscription;
    }
}
