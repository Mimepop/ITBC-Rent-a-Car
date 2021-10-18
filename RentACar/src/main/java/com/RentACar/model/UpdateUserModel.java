package com.RentACar.model;

public class UpdateUserModel {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String image;
    public UpdateUserModel(String username, String password, String firstName,
                           String lastName, String phoneNumber, String image) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
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
