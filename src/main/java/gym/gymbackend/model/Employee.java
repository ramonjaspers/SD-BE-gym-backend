package gym.gymbackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee {
    @Id
    private String id;
    private Integer salary;
    private Date dateOfEmployment;
    private Date dateTillEmployment;
    //Employee function
    private String func;
    private Integer workWeekDuration;

    // An employee can only belong to a single person
    @OneToOne
    @MapsId
    @JoinColumn(name = "username")
    private Person person;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public Date getDateTillEmployment() {
        return dateTillEmployment;
    }

    public void setDateTillEmployment(Date dateTillEmployment) {
        this.dateTillEmployment = dateTillEmployment;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public Integer getWorkWeekDuration() {
        return workWeekDuration;
    }

    public void setWorkWeekDuration(Integer workWeekDuration) {
        this.workWeekDuration = workWeekDuration;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
