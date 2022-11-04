package br.com.alura.clientelo.store;

import java.util.List;
import java.util.Optional;

public interface JPADao<T> {

    void create(T object);
    Optional<T> getById(long id);
    void update(T object);
    void remove(T object);
    Optional<List<T>> getAll();

}
