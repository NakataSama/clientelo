package br.com.alura.clientelo.store;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    void create(T object);
    Optional<T> getById(long id);
    void update(T object);
    void remove(T object);
    List<T> getAll();

}
