package com.example.libraryCoursework.controller;

import com.example.libraryCoursework.entity.AuthorImage;
import com.example.libraryCoursework.entity.BookImage;
import com.example.libraryCoursework.repository.BookImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class BookImageController {
    private final BookImageRepo bookImageRepo;

    @GetMapping("/bookImages/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        BookImage img = bookImageRepo.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", img.getOriginalFileName())
                .contentType(MediaType.valueOf(img.getContentType()))
                .contentLength(img.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(img.getBytes())));
    }
}
