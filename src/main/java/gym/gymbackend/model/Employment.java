package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "employment")
public class Employment {
    @Id
    private Long id;

    private Date dateOfEmployment;
    private Date endDateEmployment;
    private int salary;
    private String function;
    private String workWeekDuration;

    // An employment can only belong to a single person
    @OneToOne(mappedBy = "person")
    @JsonIgnore
    Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public Date getEndDateEmployment() {
        return endDateEmployment;
    }

    public void setEndDateEmployment(Date endDateEmployment) {
        this.endDateEmployment = endDateEmployment;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getWorkWeekDuration() {
        return workWeekDuration;
    }

    public void setWorkWeekDuration(String workWeekDuration) {
        this.workWeekDuration = workWeekDuration;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
