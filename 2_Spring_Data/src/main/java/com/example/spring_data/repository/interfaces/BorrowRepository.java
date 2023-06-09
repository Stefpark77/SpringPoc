package com.example.spring_data.repository.interfaces;

import com.example.spring_data.domain.Borrow;

public interface BorrowRepository {

    Integer createBorrow(Borrow borrow);

    void endBorrow(Integer accountId, Integer bookId);

    public Boolean findStatus(String accountId, String bookId);
}
