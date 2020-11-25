package br.edu.ifsp.arq.exape_dw2.repository.mapper;

import br.edu.ifsp.arq.exape_dw2.domain.model.Role;
import br.edu.ifsp.arq.exape_dw2.domain.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("role_id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .name(rs.getString("name"))
                .mail(rs.getString("mail"))
                .role(
                        Role.builder()
                        .id(rs.getLong("role_id"))
                        .name(rs.getString("role_name"))
                        .build()
                )
                .build();
    }
}
