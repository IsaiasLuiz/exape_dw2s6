package br.edu.ifsp.arq.exape_dw2.repository;

import br.edu.ifsp.arq.exape_dw2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.exape_dw2.domain.model.Category;
import br.edu.ifsp.arq.exape_dw2.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class RoleRepository implements CrudRepository<Role, Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.query("SELECT * FROM ROLE", new BeanPropertyRowMapper<>(Role.class));
    }

    @Override
    public Role findById(Long id) {
        String query = "SELECT * FROM ROLE WHERE ROLE_ID = ?";
        Role role = jdbcTemplate.query(
                query, new Object[]{id}, new BeanPropertyRowMapper<>(Role.class)).stream().findFirst().orElse(null);
        if(role == null) {
            throw new NotFoundException("Role com id " + id + " não encontrado");
        }
        return role;
    }

    @Override
    public Role save(Role entity) throws SQLException {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("ROLE").usingGeneratedKeyColumns("ROLE_ID");
        HashMap<String, String> map = new HashMap<>();
        map.put("ROLE_NAME", entity.getName());
        Number id = jdbcInsert.executeAndReturnKey(map);
        if(id != null) {
            entity.setId(id.longValue());
            return entity;
        }
        throw new SQLException();
    }

    @Override
    public Role update(Long id, Role entity) throws SQLException {
        String query = "UPDATE ROLE SET ROLE_NAME = ? WHERE ROLE_ID = ?";
        int rows = jdbcTemplate.update(query, entity.getName(), id);
        if(rows > 0) {
            return entity;
        }
        if(rows == 0) {
            throw new NotFoundException("Role com id " + id + " não encontrado");
        }
        throw new SQLException();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM ROLE WHERE ROLE_ID = ?";
        int rows = jdbcTemplate.update(query, id);
        if(rows == 0) {
            throw new NotFoundException("Role com id " + id + " não encontrado");
        }
        throw new SQLException();
    }
}
