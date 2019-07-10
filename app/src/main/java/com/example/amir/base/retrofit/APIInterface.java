package com.example.amir.base.retrofit;

import com.example.amir.base.MVVM.models.Doctors;
import com.example.amir.base.MVVM.models.Film;
import com.example.amir.base.MVVM.models.Oauth2;
import com.example.amir.base.MVVM.models.ProfileRes;
import com.example.amir.base.MVVM.models.StarWars;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

    @GET("people/?")
    Call<StarWars> getPeople(@Query("format") String format);

    @GET("Doctors/{subscriberNumber}")
    Call<Doctors> getMyDoctor(@Path("subscriberNumber") int subscriberNumber, @Query("fields") String fields);

    @GET("Doctors")
    Call<Doctors> getDoctors(@Query("fields") String fields,@Query("query") String query);

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
    Call<ProfileRes> getProfile (@Path("id") String id , @Header("Authorization") String token);

}
