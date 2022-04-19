package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // Facilities have one subscription level
    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    @JsonIgnore
    Subscription subscription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subscription getMinimumSubscription() {
        return subscription;
    }

    public void setMinimumSubscription(Subscription minimumSubscription) {
        subscription = minimumSubscription;
    }
}
