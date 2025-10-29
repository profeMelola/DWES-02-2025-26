package es.daw.jakarta.bibliotecajdbc.repository;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T,ID> {
    Optional<T> findByID (BigInteger id) throws SQLException;
    List<T> selectAll() throws SQLException;
    void insert(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(Integer id) throws SQLException;


}
