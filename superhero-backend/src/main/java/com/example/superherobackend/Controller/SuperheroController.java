package com.example.superherobackend.Controller;

import com.example.superherobackend.Model.Superhero;
import com.example.superherobackend.Service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/superheroes")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})

public class SuperheroController {
    private final SuperheroService superheroService;

    @Autowired
    public SuperheroController(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }

    @GetMapping
    public ResponseEntity<List<Superhero>> getAllSuperheroes() {
        List<Superhero> superheroes = superheroService.getAllSuperheroes();
        if (!superheroes.isEmpty()) {
            return ResponseEntity.ok().body(superheroes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Superhero> getSuperheroById(@PathVariable long id) {
        Superhero superhero = superheroService.getSuperheroById(id);
        if (superhero != null) {
            return ResponseEntity.ok().body(superhero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Superhero> addSuperhero(@RequestBody Superhero superhero) {
        Superhero createdSuperhero = superheroService.addSuperhero(superhero);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSuperhero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuperheroById(@PathVariable long id) {
        boolean isDeleted = superheroService.deleteSuperhero(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Superhero> updateSuperhero(@PathVariable Long id, @RequestBody Superhero superhero) {
        superhero.setId(id);
        Superhero updatedSuperhero = superheroService.updateSuperhero(superhero, id);
        if (updatedSuperhero != null) {
            return ResponseEntity.ok().body(updatedSuperhero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-superpower")
    public ResponseEntity<List<Superhero>> getSuperheroBySuperpower(@RequestParam String superpower) {
        List<Superhero> superheroes = superheroService.getSuperheroesBySuperpower(superpower);
        if (!superheroes.isEmpty()) {
            return ResponseEntity.ok().body(superheroes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/secret-identity")
    public ResponseEntity<List<Superhero>> getSuperheroesWithSecretIdentity(@RequestParam boolean hasSecretIdentity) {
        List<Superhero> superheroes = superheroService.getSuperheroesWithSecretIdentity(hasSecretIdentity);
        if (!superheroes.isEmpty()) {
            return ResponseEntity.ok().body(superheroes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/experience-range")
    public ResponseEntity<List<Superhero>> getSuperheroesByExperiencePointsRange(
            @RequestParam int minPoints,
            @RequestParam int maxPoints) {
        List<Superhero> superheroes = superheroService.getSuperheroesByExperiencePointsRange(minPoints, maxPoints);
        if (!superheroes.isEmpty()) {
            return ResponseEntity.ok().body(superheroes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/birth")
    public ResponseEntity<List<Superhero>> getSuperheroesBornBefore(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Superhero> superheroes = superheroService.getSuperheroesBornBefore(localDate);
        if (!superheroes.isEmpty()) {
            return ResponseEntity.ok().body(superheroes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

