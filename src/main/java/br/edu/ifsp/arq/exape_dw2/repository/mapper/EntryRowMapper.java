package br.edu.ifsp.arq.exape_dw2.repository.mapper;

import br.edu.ifsp.arq.exape_dw2.domain.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryRowMapper implements RowMapper<Entry> {
    @Override
    public Entry mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Entry.builder()
                .id(rs.getLong("entry_id"))
                .description(rs.getString("description"))
                .dueDate(rs.getDate("due_date").toLocalDate())
                .payDay(rs.getDate("pay_day").toLocalDate())
                .value(BigDecimal.valueOf(rs.getDouble("value")))
                .category(
                        Category.builder()
                                .id(rs.getLong("category_id"))
                                .name(rs.getString("category_name"))
                                .build()
                )
                .type(EntryType.valueOf(rs.getString("type")))
                .user(
                        User.builder()
                                .id(rs.getLong("user_id"))
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
                                .build()
                )
                .build();
    }
}
