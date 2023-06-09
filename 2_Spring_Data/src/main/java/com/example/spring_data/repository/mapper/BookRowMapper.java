package com.example.spring_data.repository.mapper;

import com.example.spring_data.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("id"));
        book.setAuthor(rs.getString("author"));
        book.setTitle(rs.getString("title"));
        return book;
    }
}
