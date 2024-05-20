package com.example.libraryCoursework.controller;

import com.example.libraryCoursework.entity.AuthorImage;
import com.example.libraryCoursework.repository.AuthorImageRepo;
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
public class AuthorImageController {
    private final AuthorImageRepo authorImageRepo;

    @GetMapping("/authorImages/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        AuthorImage img = authorImageRepo.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", img.getOriginalFileName())
                .contentType(MediaType.valueOf(img.getContentType()))
                .contentLength(img.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(img.getBytes())));
    }
}
