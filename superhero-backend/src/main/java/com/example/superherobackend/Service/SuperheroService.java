package com.example.superherobackend.Service;

import com.example.superherobackend.Model.Superhero;
import com.example.superherobackend.Model.Superpower;
import com.example.superherobackend.Repository.SuperheroRepository;
import com.example.superherobackend.Repository.SuperpowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuperheroService {

    private final SuperheroRepository superheroRepository;
    private final SuperpowerRepository superpowerRepository;

    @Autowired
    public SuperheroService(SuperheroRepository superheroRepository, SuperpowerRepository superpowerRepository) {
        this.superheroRepository = superheroRepository;
        this.superpowerRepository = superpowerRepository;
    }

    // READ
    public List<Superhero> getAllSuperheroes() {
        return superheroRepository.findAll();
    }

    // READ
    public Superhero getSuperheroById(long id) {
        return superheroRepository.findById(id).orElse(null);
    }

    // CREATE
    public Superhero addSuperhero(Superhero superhero) {
        List<Superpower> existingSuperpowers = updateSuperpowers(superhero.getSuperpowers());
        superhero.setSuperpowers(existingSuperpowers);
        return superheroRepository.save(superhero);
    }

    // DELETE
    public boolean deleteSuperhero(long id) {
        Optional<Superhero> optionalSuperhero = superheroRepository.findById(id);
        if (optionalSuperhero.isPresent()) {
            superheroRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // UPDATE
    public Superhero updateSuperhero(Superhero superhero, long id) {
        Optional<Superhero> existingSuperhero = superheroRepository.findById(id);
        if (existingSuperhero.isPresent()) {
            Superhero superheroToUpdate = existingSuperhero.get();
            superheroToUpdate.setName(superhero.getName());
            superheroToUpdate.setNickname(superhero.getNickname());
            superheroToUpdate.setTelephoneNumber(superhero.getTelephoneNumber());
            superheroToUpdate.setDateOfBirth(superhero.getDateOfBirth());
            superheroToUpdate.setExperiencePoints(superhero.getExperiencePoints());
            superheroToUpdate.setHasSecretIdentity(superhero.getHasSecretIdentity());
            superheroToUpdate.setSuperheroRevenue(superhero.getSuperheroRevenue());

            List<Superpower> updatedSuperpowers = updateSuperpowers(superhero.getSuperpowers());
            superheroToUpdate.setSuperpowers(updatedSuperpowers);

            return superheroRepository.save(superheroToUpdate);
        }
        return null;
    }

    // UPDATE
    private List<Superpower> updateSuperpowers(List<Superpower> superpowers) {
        List<Superpower> updatedSuperpowers = new ArrayList<>();

        for (Superpower updatedSuperpower : superpowers) {
            Superpower existingSuperpower = superpowerRepository.findBySuperpowerName(updatedSuperpower.getSuperpowerName());
            if (existingSuperpower != null) {
                updatedSuperpowers.add(existingSuperpower);
            } else {
                Superpower newSuperpower = superpowerRepository.save(updatedSuperpower);
                updatedSuperpowers.add(newSuperpower);
            }
        }

        return updatedSuperpowers;
    }

    //READ - custom
    public List<Superhero> getSuperheroesBySuperpower(String superpowerName) {
        Superpower superpower = superpowerRepository.findBySuperpowerName(superpowerName);

        if (superpower != null) {
            return superheroRepository.findBySuperpowersContaining(superpower);
        } else {
            return new ArrayList<>();
        }
    }

    // READ - custom
    public List<Superhero> getSuperheroesWithSecretIdentity(boolean hasSecretIdentity) {
        return superheroRepository.findByHasSecretIdentity(hasSecretIdentity);
    }

    //READ - custom
    public List<Superhero> getSuperheroesByExperiencePointsRange(int minPoints, int maxPoints) {
        return superheroRepository.findByExperiencePointsBetween(minPoints, maxPoints);
    }

    //READ - custom
    public List<Superhero> getSuperheroesBornBefore(LocalDate date) {
        return superheroRepository.findByDateOfBirthBefore(date);
    }
}
