package com.example.memorydb.db;

import java.util.List;
import java.util.Optional;

public interface DataRepository<T, ID> extends Repository<T, ID> {
    T save(T data);
    void delete(ID id);
    List<T> findAll();
    Optional<T> findByID(ID id);
}
