package com.example.spring_di.repository;

import com.example.spring_di.domain.Book;
import com.example.spring_di.domain.Borrow;
import com.example.spring_di.repository.interfaces.BorrowRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCBorrowRepository implements BorrowRepository {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean findStatus(String accountId, String bookId) {

        String sql = "select activeStatus " +
                "from borrow " +
                "where accountId = ? and " +
                "bookId = ? ";

        Boolean status = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, accountId);
            ps.setString(2, bookId);
            rs = ps.executeQuery();
            while (rs.next()) {
                status = rs.getBoolean("activeStatus");
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred finding status", e);
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
        return status;
    }

    public Integer createBorrow(Borrow borrow) {
        String sql = "INSERT INTO borrow" +
                "  (accountId, bookId, activeStatus) VALUES " +
                " (?, ?, true);";
        Connection conn = null;
        PreparedStatement ps = null;
        Integer rs;
        try {
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, borrow.getAccountId());
            preparedStatement.setInt(2, borrow.getBookId());
            rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred creating borrow", e);
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

    public void endBorrow(Integer bookId, Integer accountId) {
        String sql = "update borrow SET activeStatus = false where bookId = ? and accountId = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred ending a borrow", e);
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
    }


}
