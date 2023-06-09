package com.example.spring_di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Import(ApplicationConfig.class)
public class DatabaseConfig {

    /**
     * Creates an in-memory database populated
     * with test data for fast testing
     */
    @Bean
    public DataSource dataSource() {
        return (new EmbeddedDatabaseBuilder())
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();
    }
}
