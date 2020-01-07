package Dao;

import java.util.List;

public abstract interface Dao<T> {

    void add(T t);

    void update(T t);

    void remove(T t);

    void removeById(Long id);

    T getById(Long id);

    List<T> getAll();
}
