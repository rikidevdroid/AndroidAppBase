package com.example.amir.base.retrofit;

import com.example.amir.base.MVVM.models.Doctors;
import com.example.amir.base.MVVM.models.Film;
import com.example.amir.base.MVVM.models.StarWars;

import retrofit2.Call;
import retrofit2.http.GET;
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

}
