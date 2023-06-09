package com.example.spring_data.repository.interfaces;

import com.example.spring_data.domain.Account;

import java.util.List;

public interface AccountRepository {
    public Account findAccountByEmail(String email);

    public List<Account> findHistoryOfAccountsForBook(Integer bookId);

    public Integer createAccount(Account account);
}
