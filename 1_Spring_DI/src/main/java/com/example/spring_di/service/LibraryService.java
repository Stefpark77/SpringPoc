package com.example.spring_di.service;

import com.example.spring_di.domain.Account;
import com.example.spring_di.domain.Book;
import com.example.spring_di.domain.Borrow;
import com.example.spring_di.repository.interfaces.AccountRepository;
import com.example.spring_di.repository.interfaces.BookRepository;
import com.example.spring_di.repository.interfaces.BorrowRepository;

import java.util.List;

public class LibraryService {

    AccountRepository jDBCAccountRepository;
    BookRepository jDBCBookRepository;
    BorrowRepository jDBCBorrowRepository;

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
