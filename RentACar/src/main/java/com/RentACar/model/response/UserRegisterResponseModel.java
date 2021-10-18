package com.RentACar.model.response;

public class UserRegisterResponseModel {
    private boolean successfull;
    private String message;
    public UserRegisterResponseModel(boolean successfull, String message) {
        this.successfull = successfull;
        this.message = message;
    }
    public boolean isSuccessfull() {
        return successfull;
    }
    public String getMessage() {
        return message;
    }
}
