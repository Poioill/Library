package com.example.libraryCoursework.controller;

import com.example.libraryCoursework.entity.Book;
import com.example.libraryCoursework.entity.Reader;
import com.example.libraryCoursework.service.BookService;
import com.example.libraryCoursework.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ReaderService readerService;

    @PostMapping("/takeBook")
    public String getBooksByReader(@RequestParam("readerSelectG") Long readerId,
                                   @RequestParam("bookSelectG") Long bookId) {
        bookService.giveBooksByReader(readerId, bookId);
        return "redirect:/";
    }

    @PostMapping("/addBook")
    public String createAttraction(@ModelAttribute("author") Book book,
                                   @RequestParam("fileBook") MultipartFile fileBook,
                                   @RequestParam("select") Long authorId) throws IOException {
        bookService.addBook(book,fileBook, authorId);
        return "redirect:/";
    }

    @GetMapping("/catalog")
    public String bookCatalog(@RequestParam(name = "title", required = false) String title, Model model){
        model.addAttribute("book", bookService.findBookByName(title));
        return "catalog";
    }

    @GetMapping("/book/{title}")
    public String getBookById(@PathVariable String title, Model model){
        model.addAttribute("book", bookService.getBookByName(title));
        return "book";
    }

    @PostMapping("/giveBook")
    public String giveBook(@RequestParam("bookSelect") Long bookId,
                           @RequestParam("readerSelect") Long readerId){
        bookService.giveBook(bookId, readerId);
        return "redirect:/";
    }
}
