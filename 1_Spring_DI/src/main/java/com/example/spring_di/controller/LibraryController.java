package com.example.spring_di.controller;

import com.example.spring_di.domain.Account;
import com.example.spring_di.domain.Book;
import com.example.spring_di.domain.Borrow;
import com.example.spring_di.service.LibraryService;
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
        return libraryService.createAccount(account);
    }

    @PostMapping("/book")
    public Integer createBook(@RequestBody Book book) {
        return libraryService.createBook(book);
    }

    @PostMapping("/borrow")
    public Integer createBorrow(@RequestBody Borrow borrow) {
        return libraryService.createBorrow(borrow);
    }


    @PutMapping("/borrow/account/{accountId}/book/{bookId}")
    public void endBorrow(@PathVariable Integer accountId, @PathVariable Integer bookId) {
        libraryService.endBorrow(accountId, bookId);
    }


    @GetMapping("/book")
    public Book getBookByAuthorAndTitle(@RequestParam String author, @RequestParam String title) {
        return libraryService.getBookByAuthorAndTitle(author, title);
    }

    @GetMapping("/book/history")
    public List<Account> getHistoryOfAccountsForBook(@RequestParam Integer bookId) {
        return libraryService.getHistoryOfAccountsForBook(bookId);
    }

    @GetMapping("/account/books")
    public List<Book> getBooksBorrowedByAnAccount(@RequestParam Integer accountId) {
        return libraryService.getBooksBorrowedByAnAccount(accountId);
    }

    @GetMapping("/account")
    public Account getAccountByEmail(@RequestParam String email) {
        return libraryService.getAccountByEmail(email);
    }

    @GetMapping("/borrow/status")
    public Boolean getBorrowStatus(@RequestParam String accountId, @RequestParam String bookId) {
        return libraryService.getBorrowStatus(accountId, bookId);
    }

}
