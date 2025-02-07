package edu.school21.sockets.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    void save(T var1);
    void update(T var1);
    void delete(Long var1);
    void createTable();
    void deleteTable();
}
