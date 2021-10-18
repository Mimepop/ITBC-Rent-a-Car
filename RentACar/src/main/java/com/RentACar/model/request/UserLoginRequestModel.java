package com.RentACar.model.request;

public class UserLoginRequestModel {
    private String identification;
    private String password;

    public UserLoginRequestModel(String identification, String password) {
        this.identification = identification;
        this.password = password;
    }

    public String getIdentification() {
        return identification;
    }

    public String getPassword() {
        return password;
    }

}
