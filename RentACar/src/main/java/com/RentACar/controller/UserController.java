package com.RentACar.controller;

import com.RentACar.dao.UserDao;
import com.RentACar.dao.UserDaoSQL;
import com.RentACar.model.RegisterUserModel;
import com.RentACar.model.UpdateUserModel;
import com.RentACar.model.request.ChangeUserInfoRequestModel;
import com.RentACar.model.request.UserLoginRequestModel;
import com.RentACar.model.request.UserRegisterRequestModel;
import com.RentACar.model.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UserController {
    private static UserDao userDao = new UserDaoSQL();
    private boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    private boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasNumber = false;
        boolean hasLetter = false;
        char[] array = password.toCharArray();
        for (char c : array) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (hasLetter && hasNumber) {
                return true;
            }
        }
        return false;
    }
    private String getSHA256(String password) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digestedBytes){
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    //1. /users/register - POST
    @PostMapping("/users/register")
    public UserRegisterResponseModel register(@RequestBody UserRegisterRequestModel user) {
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        if (username.length() < 3) {
            return new UserRegisterResponseModel(false,
                    "Username has less than 3 characters!!");
        }
        if (!isPasswordValid(password)) {
            return new UserRegisterResponseModel(false,
                    "Password is invalid!!");
        }
        else {
            password = getSHA256(password);
        }
        if (!isEmailValid(email)) {
            return new UserRegisterResponseModel(false,
                    "Email is invalid!!");
        }
        if (userDao.userNameExists(username)) {
            return new UserRegisterResponseModel(false,
                    "Username already exists!!");
        }
        if (userDao.emailExists(email)) {
            return new UserRegisterResponseModel(false,
                    "Email already exists!!");
        }
        userDao.registerUser(new RegisterUserModel(username,email,password));
        return new UserRegisterResponseModel(true, "Successful registration");
    }
    //2. /users/login - POST
    @PostMapping("/users/login")
    public LoginResponseModel login(@RequestBody UserLoginRequestModel user) {
        String identification = user.getIdentification();
        String password = getSHA256(user.getPassword());
        if (userDao.emailExists(identification) || userDao.userNameExists(identification)) {
            if (password.equals(userDao.getPasswordWithIdentification(identification)))
                return new LoginResponseModel(true, password);
        }
        return new LoginResponseModel(false,
                "Wrong username/email or password");
    }
    //3. /users/{id} - PATCH
    @PatchMapping("/users/{id}")
    public ChangeUserInfoResponseModel changeUserInfo(@PathVariable("id") UUID id,
                                                      @RequestBody ChangeUserInfoRequestModel user) {
        String password = getSHA256(user.getPassword());
        if (!userDao.getPasswordWithId(id).equals(password)){
            return new ChangeUserInfoResponseModel(false, "Wrong password");
        }
        if (user.getNewPassword() != null && !isPasswordValid(user.getNewPassword())){
            return new ChangeUserInfoResponseModel(false, "New password is invalid");
        }
        String newPassword = getSHA256(user.getNewPassword());
        UpdateUserModel updateUser = new UpdateUserModel(user.getUsername(), newPassword, user.getFirstName(),
                user.getLastName(), user.getPhoneNumber(), user.getImage());
        userDao.updateUser(updateUser, id);
        return new ChangeUserInfoResponseModel(true, "Success");
    }
    //4. /users/{id} - GET
    @GetMapping("/users/{id}")
    public GetUserResponseModel getUser(@PathVariable("id") String id) {
        return userDao.getUser(id);
    }
    //5. /users - GET
    @GetMapping("/users")
    public List<GetUserResponseModel> getAllUsers(@RequestHeader("authorization") String id) {
        if (userDao.isAdmin(id)) {
            return userDao.getAllUsers();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
