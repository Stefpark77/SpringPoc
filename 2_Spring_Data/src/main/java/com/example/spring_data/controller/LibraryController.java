package com.example.spring_data.controller;

import com.example.spring_data.domain.Account;
import com.example.spring_data.domain.Book;
import com.example.spring_data.domain.Borrow;
import com.example.spring_data.service.LibraryService;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/account")
    public Integer createAccount(@RequestBody Account account) {
        if (StringUtil.isEmpty(account.getEmail()) || StringUtil.isEmpty(account.getName())) {
            throw new IllegalArgumentException("The email and the name must not be empty");
        }
        return libraryService.createAccount(account);
    }

    @PostMapping("/book")
    public Integer createBook(@RequestBody Book book) {
        if (StringUtil.isEmpty(book.getAuthor()) || StringUtil.isEmpty(book.getTitle())) {
            throw new IllegalArgumentException("The author and the title must not be empty");
        }
        return libraryService.createBook(book);
    }

    @PostMapping("/borrow")
    public Integer createBorrow(@RequestBody Borrow borrow) {
        if (borrow.getBorrowId() == null || borrow.getAccountId() == null) {
            throw new IllegalArgumentException("The borrowId and the accountId must not be empty");
        }
        return libraryService.createBorrow(borrow);
    }


    @PutMapping("/borrow/account/{accountId}/book/{bookId}")
    public void endBorrow(@PathVariable Integer accountId, @PathVariable Integer bookId) {
        libraryService.endBorrow(accountId, bookId);
    }


    @GetMapping("/book")
    public Book getBookByAuthorAndTitle(@RequestParam String author, @RequestParam String title) {
        if (StringUtil.isEmpty(author) || StringUtil.isEmpty(title)) {
            throw new IllegalArgumentException("The author and the title must not be empty");
        }
        return libraryService.getBookByAuthorAndTitle(author, title);
    }

    @GetMapping("/book/history")
    public List<Account> getHistoryOfAccountsForBook(@RequestParam Integer bookId) {
        if (bookId == null) {
            throw new IllegalArgumentException("The bookId must not be empty");
        }
        return libraryService.getHistoryOfAccountsForBook(bookId);
    }

    @GetMapping("/account/books")
    public List<Book> getBooksBorrowedByAnAccount(@RequestParam Integer accountId) {
        if (accountId == null) {
            throw new IllegalArgumentException("The accountId must not be empty");
        }
        return libraryService.getBooksBorrowedByAnAccount(accountId);
    }

    @GetMapping("/account")
    public Account getAccountByEmail(@RequestParam String email) {
        if (StringUtil.isEmpty(email)) {
            throw new IllegalArgumentException("The email must not be empty");
        }
        return libraryService.getAccountByEmail(email);
    }

    @GetMapping("/borrow/status")
    public Boolean getBorrowStatus(@RequestParam String accountId, @RequestParam String bookId) {
        if (bookId == null || accountId == null) {
            throw new IllegalArgumentException("The bookId must not be empty");
        }
        return libraryService.getBorrowStatus(accountId, bookId);
    }

}
