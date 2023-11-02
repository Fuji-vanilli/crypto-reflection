package org.example.repository;

import org.example.model.Crypto;

import java.util.List;
import java.util.Optional;

public interface Repository<E, T> {
    E save(E entity);
    E update(E entity);
    Optional<E> findById(T id);
    List<E> findAll();
    void delete(T id);
}
