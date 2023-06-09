package com.example.spring_di.repository;

import com.example.spring_di.domain.Account;
import com.example.spring_di.repository.interfaces.AccountRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCAccountRepository implements AccountRepository {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Account findAccountByEmail(String email) {

        String sql = "select id, accountName, email, age " +
                "from account " +
                "where email = ?";

        Account account = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            account = mapAccount(rs);
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred finding account by email", e);
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
        return account;
    }

    public List<Account> findHistoryOfAccountsForBook(Integer bookId) {

        String sql = "select id, accountName, email, age " +
                "from account where id in (select accountId from borrow where bookId = ? and activeStatus = true)";

        List<Account> accounts = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();
            accounts = mapAccounts(rs);
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred finding accounts history by book", e);
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
        return accounts;
    }

    public Integer createAccount(Account account) {
        String sql = "INSERT INTO account" +
                "  (accountName, email, age) VALUES " +
                " (?, ?, ?);";
        Connection conn = null;
        PreparedStatement ps = null;
        Integer rs;
        try {
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setInt(3, account.getAge());
            rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception occurred creating account", e);
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

    private Account mapAccount(ResultSet rs) throws SQLException {
        Account account = null;
        while (rs.next()) {
            if (account == null) {
                Integer id = rs.getInt("id");
                String accountName = rs.getString("accountName");
                String email = rs.getString("email");
                Integer age = rs.getInt("age");
                account = new Account(id, accountName, email, age);
            }
        }
        if (account == null) {
            // no rows returned - throw an empty result exception
            throw new RuntimeException("Empty result set");
        }
        return account;
    }

    private List<Account> mapAccounts(ResultSet rs) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String accountName = rs.getString("accountName");
            String email = rs.getString("email");
            Integer age = rs.getInt("age");
            accounts.add(new Account(id, accountName, email, age));
        }
        if (accounts.isEmpty()) {
            // no rows returned - throw an empty result exception
            throw new RuntimeException("Empty result set");
        }
        return accounts;
    }
}
