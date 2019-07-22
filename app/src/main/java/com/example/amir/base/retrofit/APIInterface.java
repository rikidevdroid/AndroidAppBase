package com.example.amir.base.retrofit;

import com.example.amir.base.MVVM.models.CallBooking;
import com.example.amir.base.MVVM.models.DoctorsResponse;
import com.example.amir.base.MVVM.models.OldDoctors;
import com.example.amir.base.MVVM.models.Film;
import com.example.amir.base.MVVM.models.Oauth2;
import com.example.amir.base.MVVM.models.ProfileResponse;
import com.example.amir.base.MVVM.models.StarWars;

import javax.annotation.PostConstruct;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

    @GET("people/?")
    Call<StarWars> getPeople(@Query("format") String format);

    @GET("OldDoctors/{subscriberNumber}")
    Call<OldDoctors> getMyDoctor(@Path("subscriberNumber") int subscriberNumber, @Query("fields") String fields);

    @GET("Doctors")
    Call<DoctorsResponse> getDoctors(@Query("fields") String fields);

    @GET("Doctors")
    Call<DoctorsResponse> getDoctors(@Query("fields") String fields,@Query("query") String queries);

    @GET
    Call<Film> getFilmData(@Url String url, @Query("format") String format);

    @FormUrlEncoded
    @POST("oauth2/token")
    Call<Oauth2> login(
            @Field("username") String userName,
            @Field("password") String password,
            @Field("grant_type") String grantType
    );


    @GET("Accounts/{id}/Profile")
    Call<ProfileResponse> getProfile (@Path("id") String id , @Header("Authorization") String token);


    @POST("Doctors/{subscriberNumber}/CommunicationBooking")
    Call<CallBooking> callBooking (@Path("subscriberNumber") String subscriberNumber ,@Query("patientPhoneNumber") String patientPhoneNumber );

}