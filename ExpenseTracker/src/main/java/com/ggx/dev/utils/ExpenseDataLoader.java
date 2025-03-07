package com.ggx.dev.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggx.dev.models.Expense;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExpenseDataLoader {

    @Getter
    private static List<Expense> expenseList = new ArrayList<>();

    @PostConstruct
    public void init(){
        ObjectMapper objectMapper = new ObjectMapper();
        try(InputStream is = getClass().getResourceAsStream("/expenses.json")){
            expenseList = objectMapper.readValue(is, new TypeReference<List<Expense>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
