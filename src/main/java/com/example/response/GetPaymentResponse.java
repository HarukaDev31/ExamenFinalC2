package com.example.response;

import com.example.entity.Payroll;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class GetPaymentResponse {
    private boolean success;
    private String error;
    private List<Payroll> payrolls;

    public GetPaymentResponse() {
    }



    @Override
    public String toString() {
        return "GetPaymentResponse{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", payrolls=" + payrolls +
                '}';
    }
}
