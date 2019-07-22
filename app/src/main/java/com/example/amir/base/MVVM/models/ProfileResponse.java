package com.example.amir.base.MVVM.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {

    @SerializedName("result")
    public Result result;

    @SerializedName("status")
    public String status;

    @SerializedName("code")
    public Integer code;

    @SerializedName("messages")
    public List<String> messages;


    public class Result {

        @SerializedName("profile")
        public Profile profile;


        public class Profile {

            public Profile() {

            }

            @SerializedName("firstName")
            public String firstName;

            @SerializedName("lastName")
            public String lastName;

            @SerializedName("gender")
            public String gender;

            @SerializedName("phoneNumber")
            public String phoneNumber;

            @SerializedName("image")
            public String image;

            @SerializedName("subscriberCards")
            public List<SubscriberCards> subscriberCards;

            public Profile(Profile profile) {

                this.firstName = profile.firstName;
                this.lastName = profile.lastName;
                this.gender = profile.gender;
                this.phoneNumber = profile.phoneNumber;
                this.image = profile.image;
                this.subscriberCards = profile.subscriberCards;

            }

            public Profile(String firstName, String lastName, String gender, String phoneNumber, String image, List<SubscriberCards> subscriberCards) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.gender = gender;
                this.phoneNumber = phoneNumber;
                this.image = image;
                this.subscriberCards = subscriberCards;
            }

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

        }
    }

}
