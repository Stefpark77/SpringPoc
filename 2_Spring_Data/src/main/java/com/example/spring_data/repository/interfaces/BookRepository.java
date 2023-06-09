package com.example.spring_data.repository.interfaces;

import com.example.spring_data.domain.Book;

import java.util.List;

public interface BookRepository {
    public Book findBookByAuthorAndTitle(String author, String title);

    public List<Book> findBooksBorrowedByAnAccount(Integer accountId);

    public Integer createBook(Book book);
}
