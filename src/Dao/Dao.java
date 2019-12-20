package Dao;

import java.util.List;

public abstract interface Dao<T> {

    void add(T entity);

    void update(T entity);

    void remove(T entity);

    void removeById(Long id);

    T getById(Long id);

    List<T> getAll();
}
