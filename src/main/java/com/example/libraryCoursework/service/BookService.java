package com.example.libraryCoursework.service;

import com.example.libraryCoursework.entity.Author;
import com.example.libraryCoursework.entity.Book;
import com.example.libraryCoursework.entity.BookImage;
import com.example.libraryCoursework.entity.Reader;
import com.example.libraryCoursework.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Book findBookById(Long id){
        return bookRepository.findBookById(id);
    }

    public void giveBooksByReader(Long readerId, Long bookId){
        Book book = bookRepository.findBookById(bookId);

        if (book.getReader() != null && book.getReader().getId().equals(readerId)) {
            log.info("Deleting a reader for book: {}", book.getReader());
            book.setReader(null);
            bookRepository.save(book);
        } else {
            System.out.println("No such reader with such book");
        }
    }
    public Book getBookByName(String title){
        return bookRepository.findBookByName(title);
    };

    public List<Book> findBookByName(String title){
        return bookRepository.findBookByNameContainingIgnoreCase(title);
    };

    public BookImage toImageEntity(MultipartFile file) throws IOException {
        BookImage img = new BookImage();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    public void addBook(Book book,
                                MultipartFile file, Long authorId) throws IOException {
        BookImage img;
        if (file.getSize() != 0){
            img = toImageEntity(file);
            book.addImageToBook(img);
        }
        Author author = new Author();
        author.setId(authorId);
        book.setAuthor(author);
        book.setName(book.getName());
        log.info("Saving Book: {}", book.getName());
        bookRepository.save(book);
    }

    public void giveBook(Long bookId, Long readerId){
        Book book = bookRepository.findById(bookId).orElseThrow();

        Reader reader = new Reader();
        reader.setId(readerId);

        book.setReader(reader);

        log.info("Saving a Reader for book: {}", book.getReader());
        bookRepository.save(book);
    }

}
