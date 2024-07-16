package com.alura.Literalura.repository;

import com.alura.Literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Aquí estamos esperando dos parámetros LocalDate, birthDate y deathDate
    List<Author> findByBirthDateBeforeAndDeathDateAfter(LocalDate birthDate, LocalDate deathDate);
}
