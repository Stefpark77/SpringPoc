package com.example.spring_data.service;

import com.example.spring_data.domain.Account;
import com.example.spring_data.domain.Book;
import com.example.spring_data.domain.Borrow;
import com.example.spring_data.repository.interfaces.AccountRepository;
import com.example.spring_data.repository.interfaces.BookRepository;
import com.example.spring_data.repository.interfaces.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    AccountRepository jDBCAccountRepository;
    BookRepository jDBCBookRepository;
    BorrowRepository jDBCBorrowRepository;

    @Autowired
    public LibraryService(BookRepository JDBCBookRepository, AccountRepository JDBCAccountRepository, BorrowRepository JDBCBorrowRepository) {
        this.jDBCBookRepository = JDBCBookRepository;
        this.jDBCAccountRepository = JDBCAccountRepository;
        this.jDBCBorrowRepository = JDBCBorrowRepository;
    }

    public Integer createAccount(Account account) {
        return jDBCAccountRepository.createAccount(account);
    }

    public Integer createBook(Book book) {
        return jDBCBookRepository.createBook(book);
    }

    public Integer createBorrow(Borrow borrow) {
        return jDBCBorrowRepository.createBorrow(borrow);
    }

    public void endBorrow(Integer accountId, Integer bookId) {
        jDBCBorrowRepository.endBorrow(accountId, bookId);
    }

    public Book getBookByAuthorAndTitle(String author, String title) {
        return jDBCBookRepository.findBookByAuthorAndTitle(author, title);
    }

    public List<Book> getBooksBorrowedByAnAccount(Integer accountId) {
        return jDBCBookRepository.findBooksBorrowedByAnAccount(accountId);
    }

    public Account getAccountByEmail(String email) {
        return jDBCAccountRepository.findAccountByEmail(email);
    }

    public List<Account> getHistoryOfAccountsForBook(Integer bookId) {
        return jDBCAccountRepository.findHistoryOfAccountsForBook(bookId);
    }

    public Boolean getBorrowStatus(String accountId, String bookId) {
        return jDBCBorrowRepository.findStatus(accountId, bookId);
    }


}
