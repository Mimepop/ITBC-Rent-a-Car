package com.RentACar.model.request;

public class UserUpdateRequestModel {
    private String username, password, newPassword, firstName, lastName, phoneNumber, image;

    public UserUpdateRequestModel() {
        this.username ="";
        this.password ="";
        this.newPassword="";
        this.firstName ="";
        this.lastName ="";
        this.phoneNumber="";
        this.image = "";
    }
    public UserUpdateRequestModel(String username, String password, String newPassword, String firstName,
                                  String lastName, String phoneNumber, String image) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImage() {
        return image;
    }
}
