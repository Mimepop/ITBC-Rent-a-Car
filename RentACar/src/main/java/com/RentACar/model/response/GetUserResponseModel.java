package com.RentACar.model.response;

public class GetUserResponseModel {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String personalNumber;
    private String image;

    public GetUserResponseModel(String username, String email, String firstName, String lastName,
                                String phoneNumber, String personalNumber, String image) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.personalNumber = personalNumber;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
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

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "GetUserResponseModel{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                '}';
    }
}
