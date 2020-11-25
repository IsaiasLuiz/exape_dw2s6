package br.edu.ifsp.arq.exape_dw2.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T entity) throws SQLException;

    T update(Long id, T entity) throws SQLException;

    void delete(ID id) throws SQLException;

}
