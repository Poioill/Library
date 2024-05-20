package com.example.libraryCoursework.repository;

import com.example.libraryCoursework.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByNameContainingIgnoreCase(String title);

    Book findBookByName(String title);

    Book findBookById(Long id);
}
