package br.edu.ifsp.arq.exape_dw2.repository;

import br.edu.ifsp.arq.exape_dw2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.exape_dw2.domain.model.Category;
import br.edu.ifsp.arq.exape_dw2.domain.model.Entry;
import br.edu.ifsp.arq.exape_dw2.domain.model.EntryType;
import br.edu.ifsp.arq.exape_dw2.repository.mapper.EntryRowMapper;
import br.edu.ifsp.arq.exape_dw2.repository.mapper.EntryTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntryRepository implements CrudRepository<Entry, Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Entry> findAll() {
        String sql = "SELECT * FROM ENTRY JOIN ENTRY_TYPE USING(ENTRY_TYPE_ID) JOIN CATEGORY USING (CATEGORY_ID) JOIN USERS USING (USER_ID) JOIN ROLE USING (ROLE_ID)";
        return jdbcTemplate.query(sql, new EntryRowMapper());
    }

    @Override
    public Entry findById(Long id) {
        String query = "SELECT * FROM ENTRY JOIN ENTRY_TYPE USING(ENTRY_TYPE_ID) JOIN CATEGORY USING (CATEGORY_ID) " +
                "JOIN USERS USING (USER_ID) JOIN ROLE USING (ROLE_ID) WHERE ENTRY_ID = ?";
        Entry entry = jdbcTemplate.query(
                query, new Object[]{id}, new EntryRowMapper()).stream().findFirst().orElse(null);

        if(entry == null) {
            throw new NotFoundException("Lançamento com id " + id + " não encontrado");
        }
        return entry;
    }

    @Override
    public Entry save(Entry entity) {

        return null;
    }

    @Override
    public Entry update(Long id, Entry entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
