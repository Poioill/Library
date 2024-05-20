package com.example.libraryCoursework.repository;

import com.example.libraryCoursework.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageRepo extends JpaRepository<BookImage, Long> {
}
