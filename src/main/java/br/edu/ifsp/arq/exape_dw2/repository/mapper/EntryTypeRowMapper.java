package br.edu.ifsp.arq.exape_dw2.repository.mapper;

import br.edu.ifsp.arq.exape_dw2.domain.model.EntryType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryTypeRowMapper implements RowMapper<EntryType> {
    @Override
    public EntryType mapRow(ResultSet rs, int rowNum) throws SQLException {
        String type = rs.getString("type");
        Long id = rs.getLong("entry_type_id");
        EntryType entryType = EntryType.valueOf(type);
        entryType.setId(id);
        return entryType;
    }
}
