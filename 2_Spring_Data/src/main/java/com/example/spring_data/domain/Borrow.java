package com.example.spring_data.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    Integer borrowId;
    Integer accountId;
    Integer bookId;
    Boolean active;
}
