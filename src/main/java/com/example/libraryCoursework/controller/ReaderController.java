package com.example.libraryCoursework.controller;

import com.example.libraryCoursework.entity.Reader;
import com.example.libraryCoursework.repository.ReaderRepository;
import com.example.libraryCoursework.service.ReaderService;
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
public class ReaderController {
    private final ReaderService readerService;

    @PostMapping("/addReader")
    public String addNewReader(Reader reader, @RequestParam("fileReader") MultipartFile fileReader, Model model) throws IOException {
        readerService.saveImageReader(reader, fileReader);
        return "redirect:/";
    }

    @GetMapping("/readers")
    public String readerShow(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("reader", readerService.listReader(name));
        return "readers";
    }

    @GetMapping("/reader/{id}")
    public String readerById(@PathVariable Long id, Model model){
        model.addAttribute("reader", readerService.findReaderById(id));
        return "reader";
    }

}
