package br.edu.ifsp.arq.exape_dw2.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class DatabaseInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void initDatabase() {
        createRoleTable();
        createUserTable();
        createCategoryTable();
        createEntryTypeTable();
        createEntryTable();
        insertEntryType();
        insertRole();
        insertUser();
    }

    private void createEntryTable() {
        final String query = "CREATE TABLE ENTRY(" +
                "ENTRY_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, " +
                "DESCRIPTION VARCHAR(24) NOT NULL, " +
                "DUE_DATE DATE NOT NULL, " +
                "DUE_PAY DATE NOT NULL, " +
                "VALUE DOUBLE NOT NULL, " +
                "CATEGORY_ID INTEGER NOT NULL, " +
                "USER_ID INTEGER NOT NULL, " +
                "ENTRY_TYPE_ID INTEGER NOT NULL, " +
                "PRIMARY KEY(ENTRY_ID), " +
                "FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID), " +
                "FOREIGN KEY(ENTRY_TYPE_ID) REFERENCES ENTRY_TYPE(ENTRY_TYPE_ID), " +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY(CATEGORY_ID)" +
                ")";
        jdbcTemplate.execute(query);
        log.info("CREATE TABLE ENTRY");
    }

    private void createUserTable() {
        String query = "CREATE TABLE USERS(" +
                "USER_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, " +
                "USERNAME VARCHAR(24) NOT NULL UNIQUE, " +
                "PASSWORD VARCHAR(24) NOT NULL, " +
                "NAME VARCHAR(24) NOT NULL, " +
                "MAIL VARCHAR(24) NOT NULL, " +
                "ROLE_ID INTEGER NOT NULL, " +
                "PRIMARY KEY (USER_ID), " +
                "FOREIGN KEY(ROLE_ID) REFERENCES ROLE(ROLE_ID)" +
                ")";
        jdbcTemplate.execute(query);
        log.info("CREATE TABLE USERS");
    }

    private void createRoleTable() {
        String query = "CREATE TABLE ROLE(" +
                "ROLE_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, " +
                "ROLE_NAME VARCHAR(24) NOT NULL UNIQUE, " +
                "PRIMARY KEY (ROLE_ID)" +
                ")";
        jdbcTemplate.execute(query);
        log.info("CREATE TABLE ROLE");
    }

    private void createEntryTypeTable() {
        String query = "CREATE TABLE ENTRY_TYPE(" +
                "ENTRY_TYPE_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY," +
                "TYPE VARCHAR(7) NOT NULL UNIQUE, " +
                "PRIMARY KEY (ENTRY_TYPE_ID)" +
                ")";
        jdbcTemplate.execute(query);
        log.info("CREATE TABLE ENTRY_TYPE");
    }

    private void createCategoryTable() {
        String query = "CREATE TABLE CATEGORY(" +
                "CATEGORY_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY," +
                "CATEGORY_NAME VARCHAR(24) NOT NULL UNIQUE, " +
                "PRIMARY KEY (CATEGORY_ID)" +
                ")";
        jdbcTemplate.execute(query);
        log.info("CREATE TABLE CATEGORY");
    }

    private void insertEntryType() {
        jdbcTemplate.execute("DELETE FROM ENTRY_TYPE");
        jdbcTemplate.execute("INSERT INTO ENTRY_TYPE (TYPE) VALUES ('RECEITA')");
        jdbcTemplate.execute("INSERT INTO ENTRY_TYPE (TYPE) VALUES ('DESPESA')");
        log.info("INSERT ENTRY TYPE DEFAULT");
    }

    public void insertUser() {
        jdbcTemplate.execute("DELETE FROM USERS");
        jdbcTemplate.execute("INSERT INTO USERS (USERNAME, PASSWORD, NAME, MAIL, ROLE_ID) VALUES('ISAIAS', '123', 'ISAIAS', 'ISAIAS@MAIL.COM', 1)");
        jdbcTemplate.execute("INSERT INTO USERS (USERNAME, PASSWORD, NAME, MAIL, ROLE_ID) VALUES('LUIZ', '456', 'LUIZ', 'LUIZ@MAIL.COM', 2)");
        log.info("INSERT USERS DEFAULT");
    }

    public void insertRole() {
        jdbcTemplate.execute("DELETE FROM ROLE");
        jdbcTemplate.execute("INSERT INTO ROLE(ROLE_NAME) VALUES ('ADMINISTRADOR')");
        jdbcTemplate.execute("INSERT INTO ROLE(ROLE_NAME) VALUES ('USUARIO')");
        log.info("INSERT ROLE DEFAULT");
    }

}
