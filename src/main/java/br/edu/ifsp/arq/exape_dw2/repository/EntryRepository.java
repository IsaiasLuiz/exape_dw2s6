package br.edu.ifsp.arq.exape_dw2.repository;

import br.edu.ifsp.arq.exape_dw2.domain.exception.NotFoundException;
import br.edu.ifsp.arq.exape_dw2.domain.model.Category;
import br.edu.ifsp.arq.exape_dw2.domain.model.Entry;
import br.edu.ifsp.arq.exape_dw2.domain.model.EntryType;
import br.edu.ifsp.arq.exape_dw2.domain.model.UserEntity;
import br.edu.ifsp.arq.exape_dw2.repository.mapper.EntryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class EntryRepository implements CrudRepository<Entry, Long> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntryTypeRepository entryTypeRepository;

    @Autowired
    private UserRepository userRepository;

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
    public Entry save(Entry entity) throws SQLException {
        String query =  "INSERT INTO ENTRY(DESCRIPTION, DUE_DATE, PAY_DATE, VALUE, CATEGORY_ID, ENTRY_TYPE_ID, USER_ID) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        UserEntity userEntity = entity.getUserEntity();
        if (userEntity.getId() == null) {
            userEntity = userRepository.save(entity.getUserEntity());
        }

        EntryType type = entity.getType();
        if (type.getId() == null) {
            type = entryTypeRepository.save(entity.getType());
        }

        Category category;
        try {
            category = categoryRepository.save(entity.getCategory());
        } catch (DuplicateKeyException e) {
            category = entity.getCategory();
        }

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("ENTRY").usingGeneratedKeyColumns("ENTRY_ID");

        HashMap<String, Object> map = new HashMap<>();
        map.put("DESCRIPTION", entity.getDescription());
        map.put("DUE_DATE", Date.valueOf(entity.getDueDate()));
        map.put("PAY_DAY", Date.valueOf(entity.getPayDay()));
        map.put("VALUE", entity.getValue().doubleValue());
        map.put("CATEGORY_ID", category.getId());
        map.put("ENTRY_TYPE_ID", type.getId());
        map.put("USER_ID", userEntity.getId());

        Number id = jdbcInsert.executeAndReturnKey(map);
        if(id != null) {
            entity.setId(id.longValue());
            entity.setCategory(category);
            entity.setType(type);
            entity.setUserEntity(userEntity);
            return entity;
        }
        throw new SQLException();
    }

    @Override
    public Entry update(Long id, Entry entity) throws SQLException {
        String query = "UPDATE ENTRY SET DESCRIPTION = ?, DUE_DATE = ?, PAY_DAY= ?, VALUE = ? WHERE ENTRY_ID = ?";

        categoryRepository.update(entity.getCategory().getId(), entity.getCategory());

        int rows = jdbcTemplate.update(query, entity.getDescription(), Date.valueOf(entity.getDueDate()), Date.valueOf(entity.getPayDay()), entity.getValue(), id);
        if(rows > 0) {
            return entity;
        }
        if(rows == 0) {
            throw new NotFoundException("Lançamento com id " + id + " não encontrado");
        }
        throw new SQLException();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM ENTRY WHERE ENTRY_ID = ?";
        int rows = jdbcTemplate.update(query, id);
        if(rows == 0) {
            throw new NotFoundException("Lançamento com id " + id + " não encontrado");
        }
    }

}
