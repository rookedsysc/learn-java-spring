package com.example.memorydb.book.controller;

import com.example.memorydb.book.entity.BookEntity;
import com.example.memorydb.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @PostMapping("/create")
    public BookEntity create(
            @RequestBody BookEntity bookEntity
    ) {
        return bookService.create(bookEntity);
    }

    @GetMapping("/all")
    public List<BookEntity> findAll() {
        return bookService.findAll();
    }
}
