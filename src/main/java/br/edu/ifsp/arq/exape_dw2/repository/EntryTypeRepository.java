package br.edu.ifsp.arq.exape_dw2.repository;

import br.edu.ifsp.arq.exape_dw2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.exape_dw2.domain.model.EntryType;
import br.edu.ifsp.arq.exape_dw2.repository.mapper.EntryTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class EntryTypeRepository implements CrudRepository<EntryType, Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EntryType> findAll() {
        return jdbcTemplate.query("SELECT * FROM ENTRY_TYPE", new EntryTypeRowMapper());
    }

    @Override
    public EntryType findById(Long id) {
        String query = "SELECT * FROM ENTRY_TYPE WHERE ENTRY_TYPE_ID = ?";
        EntryType entryType = jdbcTemplate.query(
                query, new Object[]{id}, new EntryTypeRowMapper()).stream().findFirst().orElse(null);
        if(entryType == null) {
            throw new NotFoundException("Tipo de lançamento com id " + id + " não encontrado");
        }
        return entryType;

    }

    @Override
    public EntryType save(EntryType entity) throws SQLException {
        String query =  "INSERT INTO ENTRY_TYPE(TYPE) VALUES(?)";
        int rows = jdbcTemplate.update(query, entity.name());
        if(rows > 0) {
            return entity;
        }
        throw new SQLException();
    }

    @Override
    public EntryType update(Long id, EntryType entity) throws SQLException {
        String query = "UPDATE ENTRY_TYPE SET TYPE = ? WHERE ENTRY_TYPE_ID = ?";
        int rows = jdbcTemplate.update(query, entity.name(), id);
        if(rows > 0) {
            return entity;
        }
        if(rows == 0) {
            throw new NotFoundException("Tipo de lançamento com id " + id + " não encontrado");
        }
        throw new SQLException();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM ENTRY_TYPE WHERE ENTRY_TYPE_ID = ?";
        int rows = jdbcTemplate.update(query, id);
        if(rows == 0) {
            throw new NotFoundException("Tipo de lançamento com id " + id + " não encontrado");
        }
        throw new SQLException();
    }
}
