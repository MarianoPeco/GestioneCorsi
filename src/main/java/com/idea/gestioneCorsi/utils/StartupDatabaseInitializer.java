package com.idea.gestioneCorsi.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class StartupDatabaseInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void loadDataAfterStartup() {
        try {
            // Load the scripts.sql script and execute it
            String sql = new String(Files.readAllBytes(Paths.get(new ClassPathResource("scripts.sql").getURI())));
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}