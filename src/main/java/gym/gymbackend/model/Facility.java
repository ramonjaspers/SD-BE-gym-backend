package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Facility {
    // Facilities have one subscription level
    @ManyToOne
    @JoinColumn(name = "minimum_membership", nullable = false)
    Membership minimumMembership;
    @ManyToOne
    @JsonIgnore
    Activity activity;
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

    public Membership getMinimumMembership() {
        return minimumMembership;
    }

    public void setMinimumMembership(Membership membership) {
        this.minimumMembership = membership;
    }
}
