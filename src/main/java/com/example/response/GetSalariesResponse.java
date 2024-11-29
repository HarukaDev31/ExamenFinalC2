package com.example.response;



import com.example.entity.Salary;
import com.example.entity.SalaryHistory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GetSalariesResponse {

    private boolean success;
    private String error;
    private List<Salary> salaries;

    @Override
    public String toString() {
        return "GetSalariesResponse{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", salaries=" + salaries +
                '}';
    }
}
