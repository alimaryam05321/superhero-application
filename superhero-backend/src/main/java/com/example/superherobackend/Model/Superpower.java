package com.example.superherobackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Superpower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String superpowerName;

    @ManyToMany(mappedBy = "superpowers")
    @JsonIgnoreProperties("superpowers")
    private List<Superhero> superheroes;

    public Superpower() {

    }

    public Superpower(long id, String superpowerName, List<Superhero> superheroes) {
        this.id = id;
        this.superpowerName = superpowerName;
        this.superheroes = superheroes;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSuperpowerName() {
        return superpowerName;
    }

    public void setSuperpowerName(String superpowerName) {
        this.superpowerName = superpowerName;
    }

    public List<Superhero> getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(List<Superhero> superheroes) {
        this.superheroes = superheroes;
    }

}

