package com.example.spring_di.repository;

import com.example.spring_di.domain.Book;
import com.example.spring_di.repository.interfaces.BookRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCBookRepository implements BookRepository {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Book findBookByAuthorAndTitle(String author, String title) {

        String sql = "select id, author, title " +
                "from book " +
                "where author = ? and " +
                "title = ? ";

        Book book = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, author);
            ps.setString(2, title);
            rs = ps.executeQuery();
            book = mapBook(rs);
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred finding book by author and title", e);
        } finally {
            if (rs != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    // Close to prevent database connection exhaustion
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return book;
    }

    public List<Book> findBooksBorrowedByAnAccount(Integer accountId) {

        String sql = "select id, author, title " +
                "from book where id in (select bookId from borrow where accountId = ? and activeStatus = true)";

        List<Book> books = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            books = mapBooks(rs);
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred finding books borrowed by account", e);
        } finally {
            if (rs != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    // Close to prevent database connection exhaustion
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return books;
    }

    public Integer createBook(Book book) {
        String sql = "INSERT INTO book" +
                "  (author, title) VALUES " +
                " (?, ?);";
        Connection conn = null;
        PreparedStatement ps = null;
        Integer rs;
        try {
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred creating book", e);
        } finally {
            if (ps != null) {
                try {
                    // Close to prevent database cursor exhaustion
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    // Close to prevent database connection exhaustion
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return rs;
    }


    private Book mapBook(ResultSet rs) throws SQLException {
        Book book = null;
        while (rs.next()) {
            if (book == null) {
                Integer id = rs.getInt("id");
                String author = rs.getString("author");
                String title = rs.getString("title");
                book = new Book(id, author, title);
            }
        }
        if (book == null) {
            // no rows returned - throw an empty result exception
            throw new RuntimeException("Empty result set");
        }
        return book;
    }

    private List<Book> mapBooks(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String author = rs.getString("author");
            String title = rs.getString("title");
            books.add(new Book(id, author, title));
        }
        if (books.isEmpty()) {
            // no rows returned - throw an empty result exception
            throw new RuntimeException("Empty result set");
        }
        return books;
    }

}
