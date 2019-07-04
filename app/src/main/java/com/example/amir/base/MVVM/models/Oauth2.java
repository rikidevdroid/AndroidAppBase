package com.example.amir.base.MVVM.models;

import com.google.gson.annotations.SerializedName;

public class Oauth2 {

    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("expires_in")
    public Integer expiresIn;


    public Oauth2(String accessToken, String tokenType, Integer expiresIn) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }
}
