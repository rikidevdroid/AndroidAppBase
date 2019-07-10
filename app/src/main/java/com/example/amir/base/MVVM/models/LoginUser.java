package com.example.amir.base.MVVM.models;

import android.util.Patterns;

public class LoginUser {

    private String userName;
    private String password;

    public LoginUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isUserNameFilled() {
        return getUserName().length()>0;
    }


    public boolean isPasswordFilled() {
        return getPassword().length() > 0;
    }

}