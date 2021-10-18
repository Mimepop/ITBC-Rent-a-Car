package com.RentACar.model;

import java.util.Date;
import java.util.UUID;

public class ContractModel {
    private UUID contractId;
    private UUID userId;
    private UUID carId;
    private Date startDate;
    private Date endDate;
    private double totalPrice;
    private boolean signed;
    private boolean approved;

    public ContractModel(UUID contractId, UUID userId, UUID carId, Date startDate, Date endDate, double totalPrice,
                         boolean signed, boolean approved) {
        this.contractId = contractId;
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.signed = signed;
        this.approved = approved;
    }

    public UUID getContractId() {
        return contractId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public void setContractId(UUID contractId) {
        this.contractId = contractId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
