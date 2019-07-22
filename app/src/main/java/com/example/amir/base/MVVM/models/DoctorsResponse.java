package com.example.amir.base.MVVM.models;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class DoctorsResponse {

    @SerializedName("result")
    public  Result result;

    @SerializedName("status")
    public String status;

    @SerializedName("code")
    public Integer code;

    @SerializedName("messages")
    public List<String> messages;

    public class Result {

        @SerializedName("doctors")
        public List<Doctor> doctors;

        @SerializedName("doctorsTotalCount")
        public Integer doctorsTotalCount;


        public class Doctor {

            @SerializedName("id")
            public  String id;

            @SerializedName("imagePath")
            public  String imagePath;

            @SerializedName("subscriberNumber")
            public String subscriberNumber;

            @SerializedName("gender")
            public String gender;

            @SerializedName("title")
            public  String title;

            @SerializedName("firstName")
            public String firstName;

            @SerializedName("lastName")
            public String lastName;

            @SerializedName("medicalCouncilNumber")
            public  String medicalCouncilNumber;

            @SerializedName("expertise")
            public String expertise;

            @SerializedName("currentlyAvailable")
            public Boolean currentlyAvailable;

            @SerializedName("providesDiagnosticDocumentsService")
            public  Boolean providesDiagnosticDocumentsService;

            @SerializedName("specialty")
            public MedicalSpecialty specialty;

            @SerializedName("tags")
            public List<Tag> tags;

            @SerializedName("timetable")
            public  Timetable timetable;

            @SerializedName("workplaces")
            public List<Workplace> workplaces;

            public class Workplace {


                @SerializedName("title")
                public String title;

                @SerializedName("street")
                public String street;

                @SerializedName("phoneNumber")
                public String phoneNumber;

                @SerializedName("description")
                public String description;

                @SerializedName("longitude")
                public BigDecimal longitude;

                @SerializedName("latitude")
                public BigDecimal latitude;


            }




            public class Timetable {

                @SerializedName("segments")
                public List<FromTo> segments;

                public class FromTo {

                    @SerializedName("from")
                    public  Integer from;

                    @SerializedName("to")
                    public  Integer to;

                }


            }


            public class Tag {

                @SerializedName("id")
                public Integer id;

                @SerializedName("title")
                public String title;

            }


            public class MedicalSpecialty {

                @SerializedName("id")
                public Integer id;

                @SerializedName("title")
                public String title;

                @SerializedName("description")
                public String description;

                @SerializedName("imagePath")
                public String imagePath;


            }


        }


    }


}
