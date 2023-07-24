package com.example.memorydb.book.service;

import com.example.memorydb.book.entity.BookEntity;
import com.example.memorydb.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    BookService(BookRepository repo) {
        this.repo = repo;
    }

    public BookEntity create(BookEntity bookEntity) {
        return repo.save(bookEntity);
    }

    public void delete(Long id) {
//        repo.delete(id);
    }

    public List<BookEntity> findAll() {
        return repo.findAll();
    }
}
