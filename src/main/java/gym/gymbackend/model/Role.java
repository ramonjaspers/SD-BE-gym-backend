package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    Long id;

    // A role has many persons
    @OneToMany(mappedBy = "person")
    @JsonIgnore
    List<Person> person = new ArrayList<>();

    @NotEmpty
    @Size(min = 2, max = 255)
    String name;


}
