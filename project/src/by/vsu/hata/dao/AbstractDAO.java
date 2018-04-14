package by.vsu.hata.dao;

import java.util.List;

public interface AbstractDAO<E, I> {

    List<E> getAll();
    E getById(I id);
    void insert(E entity);
    void delete(E entity);
    void deleteAll();
}
