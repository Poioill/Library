package com.example.libraryCoursework.repository;

import com.example.libraryCoursework.entity.AuthorImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorImageRepo extends JpaRepository<AuthorImage, Long> {
}
