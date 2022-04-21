package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private Date dateOfEmployment;
    private Date endDateEmployment;
    private Integer salary;
    //Employee function
    private String func;
    private Integer workWeekDuration;

    // An employee can only belong to a single person
    @OneToOne(mappedBy = "employee")
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
}
