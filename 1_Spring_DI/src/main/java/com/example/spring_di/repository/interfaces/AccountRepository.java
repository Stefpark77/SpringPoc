package com.example.spring_di.repository.interfaces;

import com.example.spring_di.domain.Account;

import java.util.List;

public interface AccountRepository {
    public Account findAccountByEmail(String email);

    public List<Account> findHistoryOfAccountsForBook(Integer bookId);

    public Integer createAccount(Account account);
}
