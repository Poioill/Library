package com.example.libraryCoursework.controller;

import com.example.libraryCoursework.entity.Author;
import com.example.libraryCoursework.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/addAuthor")
    public String addNewAuthor(Author author, @RequestParam("fileAuthor") MultipartFile fileAuthor, Model model) throws IOException {
        authorService.saveImageAuthor(author, fileAuthor);
        return "redirect:/";
    }

    @GetMapping("/author/{name}")
    public String getAuthorByName(@PathVariable String name, Model model){
        model.addAttribute("author", authorService.getAuthorByName(name));
        return "authorMore";
    }

    @GetMapping("/author")
    public String getAuthors(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("author", authorService.getAllAuthors(name));
        return "author";
    }
}
