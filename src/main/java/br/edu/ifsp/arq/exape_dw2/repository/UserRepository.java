package br.edu.ifsp.arq.exape_dw2.repository;

import br.edu.ifsp.arq.exape_dw2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.exape_dw2.domain.model.User;
import br.edu.ifsp.arq.exape_dw2.repository.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository implements CrudRepository<User, Long>{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM USER JOIN ROLE USING(ROLE_ID)", new UserRowMapper());
    }

    @Override
    public User findById(Long id) {
        String query = "SELECT * FROM USER JOIN ROLE USING(ROLE_ID) WHERE USER_ID = ?";
        User user = jdbcTemplate.query(
                query, new Object[]{id}, new UserRowMapper()).stream().findFirst().orElse(null);
        if(user == null) {
            throw new NotFoundException("Usuario com id " + id + " não encontrado");
        }
        return user;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public User update(Long id, User entity) throws SQLException {
        String query = "UPDATE USER SET USER_ID = ? WHERE USER_ID = ?";
        roleRepository.update(entity.getRole().getId(), entity.getRole());
        int rows = jdbcTemplate.update(query, entity.getName(), id);
        if(rows > 0) {
            return entity;
        }
        if(rows == 0) {
            throw new NotFoundException("Usuario com id " + id + " não encontrado");
        }
        throw new SQLException();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM USER WHERE USER_ID = ?";
        int rows = jdbcTemplate.update(query, id);
        if(rows == 0) {
            throw new NotFoundException("Usuario com id " + id + " não encontrado");
        }
        throw new SQLException();
    }
}
