package com.RentACar.model.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class ContractResponseModel {
    private String contractId;
    private String userId;
    private String carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private boolean signed;
    private boolean approved;
    public ContractResponseModel(String contractId, String userId, String carId, LocalDate startDate, LocalDate endDate,
                                 double totalPrice, boolean signed, boolean approved) {
        this.contractId = contractId;
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.signed = signed;
        this.approved = approved;
    }
    public String getContractId() {
        return contractId;
    }
    public String getUserId() {
        return userId;
    }
    public String getCarId() {
        return carId;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public boolean isSigned() {
        return signed;
    }
    public boolean isApproved() {
        return approved;
    }
}
