package br.edu.ifsp.arq.exape_dw2.config;

import br.edu.ifsp.arq.exape_dw2.repository.DatabaseInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatabaseConfiguration implements CommandLineRunner {

    @Autowired
    private DatabaseInitializer databaseInitializer;

    @Override
    public void run(String... args) throws Exception {
        log.info("INITIALIZING DATABASE");
        databaseInitializer.initDatabase();
    }

}
