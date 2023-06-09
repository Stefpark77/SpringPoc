package com.example.spring_data.repository;

import com.example.spring_data.domain.Account;
import com.example.spring_data.repository.interfaces.AccountRepository;
import com.example.spring_data.repository.mapper.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JDBCAccountRepository implements AccountRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<Account> rowMapper = new AccountRowMapper();

    @Autowired
    public JDBCAccountRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account findAccountByEmail(String email) {
        String sql = "select id, accountName, email, age from account where email = :email";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("email", email);
        return jdbcTemplate.queryForObject(sql, namedParameters, rowMapper);
    }

    public List<Account> findHistoryOfAccountsForBook(Integer bookId) {

        String sql = "select id, accountName, email, age " +
                "from account where id in (select accountId from borrow where bookId = :bookId and activeStatus = true)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("bookId", bookId);
        return jdbcTemplate.query(sql, namedParameters, rowMapper);
    }

    public Integer createAccount(Account account) {
        String sql = "INSERT INTO account" +
                "  (accountName, email, age) VALUES " +
                " (:accountName, :email, :age);";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("accountName", account.getAccountId())
                .addValue("email", account.getEmail())
                .addValue("age", account.getAge());
        return jdbcTemplate.update(sql, namedParameters);
    }

}
