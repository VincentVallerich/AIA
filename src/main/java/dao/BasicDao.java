package dao;

import java.util.List;
import java.util.Optional;

public interface BasicDao<T> {
    List<T> findAll();
    Optional<T> findById(long id);
    void delete(long id);
    boolean insert(T t);
}
