package com.example.amir.base.MVVM.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

public class OldDoctors {

    @SerializedName("doctors") @Expose public List<Doctor> result ;

    public class Doctor{

        @SerializedName("firstName")
        @Expose public String firstName;
        @SerializedName("lastName")
        @Expose public String lastName;


    }


}
