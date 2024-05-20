package com.example.libraryCoursework.controller;

import com.example.libraryCoursework.service.AuthorService;
import com.example.libraryCoursework.service.BookService;
import com.example.libraryCoursework.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final ReaderService readerService;

    @GetMapping("/")
    public String openHome(Model model){
        model.addAttribute("author", authorService.findAllAuthors());
        model.addAttribute("book", bookService.findAllBooks());
        model.addAttribute("reader", readerService.findAllReaders());
        return "index";
    }

}
