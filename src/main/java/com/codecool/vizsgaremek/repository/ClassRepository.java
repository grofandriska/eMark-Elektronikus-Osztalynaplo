package com.codecool.vizsgaremek.repository;

import com.codecool.vizsgaremek.modell.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long> {

     Optional<Class> findByName(String name);
}
