package org.ggx.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;

@Data
public class Expense {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("expenseType")
    private Long type;

    @JsonProperty("date")
    private String date;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("category")
    private String category;

    @JsonProperty("account")
    private String account;

    @JsonProperty("note")
    private String note;
}
