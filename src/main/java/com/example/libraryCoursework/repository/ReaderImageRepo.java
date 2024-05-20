package com.example.libraryCoursework.repository;

import com.example.libraryCoursework.entity.ReaderImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderImageRepo extends JpaRepository<ReaderImage, Long> {
}
