package com.RentACar.model.request;

import java.time.LocalDate;

public class ContractSampleRequestModel {
    private String userId;
    private String carId;
    private LocalDate startDate;
    private LocalDate endDate;

    public ContractSampleRequestModel(String userId, String carId, LocalDate startDate, LocalDate endDate) {
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
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
}
