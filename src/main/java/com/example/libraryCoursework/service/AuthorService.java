package com.example.libraryCoursework.service;

import com.example.libraryCoursework.entity.Author;
import com.example.libraryCoursework.entity.AuthorImage;
import com.example.libraryCoursework.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors(String name){
        if (name == null){
            return authorRepository.findAll();
        } else {
            return authorRepository.findAuthorByNameContainingIgnoreCase(name);
        }
    }

    public List<Author> findAllAuthors(){
        return authorRepository.findAll();
    }
    public Author getAuthorByName(String name){
        return authorRepository.findAuthorByName(name);
    }

    public AuthorImage toImageEntity(MultipartFile file) throws IOException {
        AuthorImage img = new AuthorImage();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    public void saveImageAuthor(Author author,
                                MultipartFile file) throws IOException {
        AuthorImage img;
        if (file.getSize() != 0){
            img = toImageEntity(file);
            author.addImageToAuthor(img);
        }
        author.setName(author.getName());
        log.info("Saving Author: {}", author.getName());
        authorRepository.save(author);
    }
}
