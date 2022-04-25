package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Subscription {
    @Id
    @GeneratedValue
    @JsonIgnore
    private String id;
    private Date subscriptionStartDate;
    private Date subscriptionEndDate;

    // one membership has one person
    @OneToOne
    @MapsId
    @JoinColumn(name = "username")
    @JsonIgnore
    private Person person;

    @ManyToOne
    @JoinColumn(name = "membership")
    private Membership membership;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public Date getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Membership getMembership() {
        return membership;
    }

    public void getMembership(Membership membership) {
        this.membership = membership;
    }
}
