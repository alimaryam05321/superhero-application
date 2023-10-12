package com.example.superherobackend.Repository;

import com.example.superherobackend.Model.Superhero;
import com.example.superherobackend.Model.Superpower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

    List<Superhero> findBySuperpowersContaining(Superpower superpower);
    List<Superhero> findByHasSecretIdentity(boolean hasSecretIdentity);
    List<Superhero> findByExperiencePointsBetween(int minPoints, int maxPoints);
    List<Superhero> findByDateOfBirthBefore(LocalDate date);

}
