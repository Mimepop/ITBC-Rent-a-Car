package com.RentACar.controller;

import com.RentACar.dao.UserDao;
import com.RentACar.dao.UserDaoSQL;
import com.RentACar.model.AdminUpdateUserModel;
import com.RentACar.model.request.AdminChangeUserInfoRequestModel;
import com.RentACar.model.response.AdminChangeUserInfoResponseModel;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public class AdminController {
    UserDao userDao = new UserDaoSQL();

    private boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    //1. /admin/update/{id} - PATCH
    @PatchMapping("/admin/update/{id}")
    public AdminChangeUserInfoResponseModel adminChangeUserInformation(@PathVariable("id") String userId,
                                                                       @RequestHeader("authorization") String adminId,
                                                                       @RequestBody AdminChangeUserInfoRequestModel info) {
        if (!userDao.isAdmin(adminId)) {
            return new AdminChangeUserInfoResponseModel
                    (false, "Only Admin can access this option");
        }

        String username = info.getUsername();
        String email = info.getEmail();

        if ((!username.isEmpty()) && username.length() < 3) {
            username = "";
        }
        if ((!email.isEmpty()) && !isEmailValid(email)) {
            email = "";
        }
        if (username.isEmpty() && email.isEmpty() && info.getFirstName().isEmpty()
                && info.getLastName().isEmpty() && info.getPersonalNumber().isEmpty()
                && info.getPhoneNumber().isEmpty() && info.getImage().isEmpty()) {
            return new AdminChangeUserInfoResponseModel(false, "Update is not successful");
        }
        AdminUpdateUserModel userInfo = new AdminUpdateUserModel(username, email, info.getFirstName(),
                info.getLastName(), info.getPhoneNumber(), info.getPersonalNumber(), info.getImage());

        userDao.adminUpdateUserInfo(userInfo, userId);
        return new AdminChangeUserInfoResponseModel(true, "Success");
    }
}
