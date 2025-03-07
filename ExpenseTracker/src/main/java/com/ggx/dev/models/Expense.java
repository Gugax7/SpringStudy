package com.ggx.dev.models;

import lombok.Data;

@Data
public class Expense {


    private Long id;
    private Integer expenseType;
    private String date;
    private Double amount;
    private String category;
    private String account;
    private String note;


}
