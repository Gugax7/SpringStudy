package com.ggx.dev;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Component
public class ExpenseDataLoader {

    @Getter
    private static List<Expense> expenses;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/expenses.json");
            expenses = mapper.readValue(is, new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}