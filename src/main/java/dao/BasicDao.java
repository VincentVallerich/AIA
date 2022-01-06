package dao;

import java.util.List;

public interface BasicDao<T> {
    List<T> findAll();
    T findById(long id);
    void delete(long id);
    boolean insert(T t);
}
