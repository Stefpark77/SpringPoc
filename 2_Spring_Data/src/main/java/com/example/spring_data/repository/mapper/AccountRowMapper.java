package com.example.spring_data.repository.mapper;

import com.example.spring_data.domain.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccountId(rs.getInt("id"));
        account.setName(rs.getString("accountName"));
        account.setEmail(rs.getString("email"));
        account.setAge(rs.getInt("age"));
        return account;
    }
}
