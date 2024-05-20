package com.example.libraryCoursework.repository;

import com.example.libraryCoursework.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByName(String name);
    List<Author> findAuthorByNameContainingIgnoreCase(String name);
}
