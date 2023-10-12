package com.example.superherobackend.Repository;

import com.example.superherobackend.Model.Superpower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperpowerRepository extends JpaRepository<Superpower, Long> {
    Superpower findBySuperpowerName(String superpowerName);
}

