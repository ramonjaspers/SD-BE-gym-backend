package gym.gymbackend.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gym.gymbackend.enums.Sex;
import gym.gymbackend.model.Authority;
import gym.gymbackend.model.Subscription;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class PersonDto {
    @NotBlank
    @Size(max = 128)
    public String username;
    @NotBlank
    @Size(max = 128)
    public String password;
    @NotBlank
    @Size(max = 128)
    public String name;
    @Size(max = 128)
    public String address;
    public String bankNumber;
    @Past(message = "Date must be in the past!")
    public Date dateOfBirth;
    @Max(value = 10000, message = "Credit too high, check the credit value or contact the administrator")
    public Double credit;
    @NotBlank
    public Sex sex;
    @Size(max = 128)
    public String apiKey;
    @JsonSerialize
    public Set<Authority> authorities;
    public Subscription subscription;
    public String picture;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
