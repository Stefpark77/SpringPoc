package com.example.spring_data.repository;

import com.example.spring_data.domain.Book;
import com.example.spring_data.repository.interfaces.BookRepository;
import com.example.spring_data.repository.mapper.BookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class JDBCBookRepository implements BookRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<Book> rowMapper = new BookRowMapper();

    @Autowired
    public JDBCBookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Book findBookByAuthorAndTitle(String author, String title) {

        String sql = "select id, author, title from book where author = :author and title = :title ";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("author", author)
                .addValue("title", title);
        return jdbcTemplate.queryForObject(sql, namedParameters, rowMapper);
    }

    public List<Book> findBooksBorrowedByAnAccount(Integer accountId) {

        String sql = "select id, author, title " +
                "from book where id in (select bookId from borrow where accountId = :accountId and activeStatus = true)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("accountId", accountId);
        return jdbcTemplate.query(sql, namedParameters, rowMapper);
    }

    public Integer createBook(Book book) {
        String sql = "INSERT INTO book" +
                "  (author, title) VALUES " +
                " (:author, :title);";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("author", book.getAuthor())
                .addValue("title", book.getTitle());
        return jdbcTemplate.update(sql, namedParameters);
    }

}
