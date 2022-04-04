package gym.gymbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gym.gymbackend.enums.Sex;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String address;
    private String bankNumber;
    private  Date dateOfBirth;
    private Double credit;
    private  Sex sex;
    private String picture;
    private  String email;
    private  Integer salary;
    private Date dateOfEmployment;
    private LocalDateTime timeStamp;

    // A Person has none or one employment
    @OneToOne(mappedBy = "employment")
    @JsonIgnore
    Employment employement;

    // Multiple persons have a subscription
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonIgnore
    Subscription subscription;

    // Multiple persons have a subscription
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @JsonIgnore
    Role role;

    // Multiple persons have a subscription
    @OneToMany(mappedBy = "workout")
    @JsonIgnore
    List<Workout> workouts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Employment getEmployement() {
        return employement;
    }

    public void setEmployement(Employment employement) {
        this.employement = employement;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }
}
