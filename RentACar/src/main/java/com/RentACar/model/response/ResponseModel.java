package com.RentACar.model.response;

public class ResponseModel {
    Boolean success = false;
    String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ResponseModel(Boolean success, String info) {
        this.success = success;
        this.info = info;
    }
    public ResponseModel(Boolean success) {
        this.success = success;
    }
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
