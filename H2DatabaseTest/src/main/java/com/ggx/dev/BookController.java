package com.ggx.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<String> saveBook(@RequestBody Book book){
        bookService.saveBook(book);
        return new ResponseEntity<>("Book %s added successfully".formatted(book.getTitle()), HttpStatus.CREATED);
    }
}
