package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double Price;

    // A subscription has multiple persons
    @OneToMany(mappedBy = "person")
    @JsonIgnore
    List<Person> persons = new ArrayList<>();

    // One subscription has many facilities to access
    @OneToMany(mappedBy = "facility")
    @JsonIgnore
    List<Facility> facilities = new ArrayList<>();

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

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }
}
