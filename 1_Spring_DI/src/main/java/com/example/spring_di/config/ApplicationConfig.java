package com.example.spring_di.config;

import com.example.spring_di.controller.LibraryController;
import com.example.spring_di.repository.JDBCBookRepository;
import com.example.spring_di.repository.JDBCAccountRepository;
import com.example.spring_di.repository.JDBCBorrowRepository;
import com.example.spring_di.repository.interfaces.AccountRepository;
import com.example.spring_di.repository.interfaces.BookRepository;
import com.example.spring_di.repository.interfaces.BorrowRepository;
import com.example.spring_di.service.LibraryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    private DataSource dataSource;

    // As this is the only constructor, @Autowired is not needed.
    public ApplicationConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public LibraryService libraryService() {
        return new LibraryService(
                bookRepository(),
                accountRepository(),
                borrowRepository());
    }

    @Bean
    public BookRepository bookRepository() {
        JDBCBookRepository JDBCBookRepository = new JDBCBookRepository();
        JDBCBookRepository.setDataSource(dataSource);
        return JDBCBookRepository;
    }

    @Bean
    public AccountRepository accountRepository() {
        JDBCAccountRepository JDBCAccountRepository = new JDBCAccountRepository();
        JDBCAccountRepository.setDataSource(dataSource);
        return JDBCAccountRepository;
    }

    @Bean
    public BorrowRepository borrowRepository() {
        JDBCBorrowRepository JDBCBorrowRepository = new JDBCBorrowRepository();
        JDBCBorrowRepository.setDataSource(dataSource);
        return JDBCBorrowRepository;
    }
}
