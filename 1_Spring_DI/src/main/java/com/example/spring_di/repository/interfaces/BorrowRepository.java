package com.example.spring_di.repository.interfaces;

import com.example.spring_di.domain.Borrow;

public interface BorrowRepository {

    Integer createBorrow(Borrow borrow);

    void endBorrow(Integer accountId, Integer bookId);

    public Boolean findStatus(String accountId, String bookId);
}
