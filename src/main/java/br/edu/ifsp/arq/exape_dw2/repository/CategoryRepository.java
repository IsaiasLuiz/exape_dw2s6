package br.edu.ifsp.arq.exape_dw2.repository;

import br.edu.ifsp.arq.exape_dw2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.exape_dw2.domain.model.Category;
import br.edu.ifsp.arq.exape_dw2.repository.mapper.EntryTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class CategoryRepository implements CrudRepository<Category, Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * FROM CATEGORY", new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public Category findById(Long id) {
        String query = "SELECT * FROM CATEGORY WHERE CATEGORY_ID = ?";
        Category category = jdbcTemplate.query(
                query, new Object[]{id}, new BeanPropertyRowMapper<>(Category.class)).stream().findFirst().orElse(null);
        if(category == null) {
            throw new NotFoundException("Categoria com id " + id + " não encontrado");
        }
        return category;
    }

    @Override
    public Category save(Category entity) throws SQLException {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("CATEGORY").usingGeneratedKeyColumns("CATEGORY_ID");
        HashMap<String, String> map = new HashMap<>();
        map.put("CATEGORY_NAME", entity.getName());
            Number id = jdbcInsert.executeAndReturnKey(map);
        if(id != null) {
            entity.setId(id.longValue());
            return entity;
        }
        throw new SQLException();
    }

    @Override
    public Category update(Long id, Category entity) throws SQLException {
        String query = "UPDATE CATEGORY SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?";
        int rows = jdbcTemplate.update(query, entity.getName(), id);
        if(rows > 0) {
            return entity;
        }
        if(rows == 0) {
            throw new NotFoundException("Categoria com id " + id + " não encontrado");
        }
        throw new SQLException();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM CATEGORY WHERE CATEGORY_ID = ?";
        int rows = jdbcTemplate.update(query, id);
        if(rows == 0) {
            throw new NotFoundException("Categoria com id " + id + " não encontrado");
        }
        throw new SQLException();
    }
}
