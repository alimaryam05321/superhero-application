package com.example.superherobackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String nickname;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "superhero_superpower",
            joinColumns = @JoinColumn(name = "superhero_id"),
            inverseJoinColumns = @JoinColumn(name = "superpower_id")
    )
    @JsonIgnoreProperties("superheroes")
    private List<Superpower> superpowers;

    private String telephoneNumber;
    private LocalDate dateOfBirth;
    private int experiencePoints;
    private boolean hasSecretIdentity;
    private double superheroRevenue;


    // Default constructor (required by JPA)
    public Superhero() {

    }

    public Superhero(long id, String name, String nickname, List<Superpower> superpowers, String telephoneNumber, LocalDate dateOfBirth, int experiencePoints, boolean hasSecretIdentity, double superheroRevenue) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.superpowers = superpowers;
        this.telephoneNumber = telephoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.experiencePoints = experiencePoints;
        this.hasSecretIdentity = hasSecretIdentity;
        this.superheroRevenue = superheroRevenue;
    }

    @Override
    public String toString() {
        return "Superhero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", superpowers=" + superpowers +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", experiencePoints=" + experiencePoints +
                ", hasSecretIdentity=" + hasSecretIdentity +
                ", superheroRevenue=" + superheroRevenue +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Superpower> getSuperpowers() {
        return superpowers;
    }

    public void setSuperpowers(List<Superpower> superpowers) {
        this.superpowers = superpowers;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public boolean getHasSecretIdentity() {
        return hasSecretIdentity;
    }

    public void setHasSecretIdentity(boolean hasSecretIdentity) {
        this.hasSecretIdentity = hasSecretIdentity;
    }

    public double getSuperheroRevenue() {
        return superheroRevenue;
    }

    public void setSuperheroRevenue(double superheroRevenue) {
        this.superheroRevenue = superheroRevenue;
    }

}
