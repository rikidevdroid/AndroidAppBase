package com.example.amir.base.MVVM.models;

import com.google.gson.annotations.SerializedName;

public class SubscriberCards {

    @SerializedName("subscriberNumber")
    public String subscriberNumber;

    @SerializedName("credit")
    public Integer credit;

    @SerializedName("state")
    public String state;

    public SubscriberCards(String subscriberNumber, Integer credit, String state) {
        this.subscriberNumber = subscriberNumber;
        this.credit = credit;
        this.state = state;
    }
}
