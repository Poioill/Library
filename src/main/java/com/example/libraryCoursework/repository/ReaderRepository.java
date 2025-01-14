package com.example.libraryCoursework.repository;

import com.example.libraryCoursework.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    List<Reader> findByNameContainingIgnoreCase(String name);

    Reader findReaderById(Long id);
}
