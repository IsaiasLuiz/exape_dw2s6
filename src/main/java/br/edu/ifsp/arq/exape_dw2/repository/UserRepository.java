package br.edu.ifsp.arq.exape_dw2.repository;

import br.edu.ifsp.arq.exape_dw2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.exape_dw2.domain.model.UserEntity;
import br.edu.ifsp.arq.exape_dw2.repository.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository implements CrudRepository<UserEntity, Long>{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM USERS JOIN ROLE USING(ROLE_ID)", new UserRowMapper());
    }

    @Override
    public UserEntity findById(Long id) {
        String query = "SELECT * FROM USERS JOIN ROLE USING(ROLE_ID) WHERE USER_ID = ?";
        UserEntity userEntity = jdbcTemplate.query(
                query, new Object[]{id}, new UserRowMapper()).stream().findFirst().orElse(null);
        if(userEntity == null) {
            throw new NotFoundException("Usuario com id " + id + " não encontrado");
        }
        return userEntity;
    }

    public UserEntity findByUsernameIgnoreCase(String username) {
        String query = "SELECT * FROM USERS JOIN ROLE USING(ROLE_ID) WHERE UPPER(USERNAME) = UPPER(?)";
        UserEntity userEntity = jdbcTemplate.query(
                query, new Object[]{username}, new UserRowMapper()).stream().findFirst().orElse(null);
        if(userEntity == null) {
            throw new NotFoundException("Usuario com username " + username + " não encontrado");
        }
        return userEntity;
    }

    @Override
    public UserEntity save(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity update(Long id, UserEntity entity) throws SQLException {
        String query = "UPDATE USERS SET USER_ID = ? WHERE USER_ID = ?";
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
        String query = "DELETE FROM USERS WHERE USER_ID = ?";
        int rows = jdbcTemplate.update(query, id);
        if(rows == 0) {
            throw new NotFoundException("Usuario com id " + id + " não encontrado");
        }
        throw new SQLException();
    }
}
