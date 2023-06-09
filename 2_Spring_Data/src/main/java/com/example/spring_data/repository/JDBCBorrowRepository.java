package com.example.spring_data.repository;

import com.example.spring_data.domain.Borrow;
import com.example.spring_data.repository.interfaces.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class JDBCBorrowRepository implements BorrowRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCBorrowRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean findStatus(String accountId, String bookId) {

        String sql = "select activeStatus from borrow where accountId = :accountId and bookId = :bookId ";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("accountId", accountId)
                .addValue("bookId", bookId);
        return jdbcTemplate.queryForObject(sql, namedParameters, Boolean.class);
    }

    public Integer createBorrow(Borrow borrow) {
        String sql = "INSERT INTO borrow" +
                "  (accountId, bookId, activeStatus) VALUES " +
                " (:accountId, :bookId, true);";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("accountId", borrow.getAccountId())
                .addValue("bookId", borrow.getBookId());
        return jdbcTemplate.update(sql, namedParameters);
    }

    public void endBorrow(Integer bookId, Integer accountId) {
        String sql = "update borrow SET activeStatus = false where bookId = :bookId and accountId = :accountId";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("accountId", accountId)
                .addValue("bookId", bookId);
        jdbcTemplate.update(sql, namedParameters);
    }


}
