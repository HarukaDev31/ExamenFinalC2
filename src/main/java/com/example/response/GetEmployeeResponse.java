package com.example.response;

import java.util.List;
import com.example.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class GetEmployeeResponse {
    private boolean success;
    private String error;
    private List<Employee> employees;



    @Override
    public String toString() {
        return "GetEmployeeResponse{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", employees=" + employees +
                '}';
    }
}
