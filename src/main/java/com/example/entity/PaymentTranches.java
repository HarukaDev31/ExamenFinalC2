package com.example.entity;

public class PaymentTranches {
    private Long id;
    private String name;
    private double infLimit;
    private double supLimit;
    private double discount;

    public PaymentTranches(Long id, String name, double infLimit, double supLimit, double discount) {
        this.id = id;
        this.name = name;
        this.infLimit = infLimit;
        this.supLimit = supLimit;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInfLimit() {
        return infLimit;
    }

    public void setInfLimit(double infLimit) {
        this.infLimit = infLimit;
    }

    public double getSupLimit() {
        return supLimit;
    }

    public void setSupLimit(double supLimit) {
        this.supLimit = supLimit;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
