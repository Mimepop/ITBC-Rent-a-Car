package com.RentACar.model.response;

public class LoginResponseModel {
    private boolean successful;
    private String info;

    public LoginResponseModel(boolean successful, String info) {
        this.successful = successful;
        this.info = info;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getInfo() {
        return info;
    }
}
