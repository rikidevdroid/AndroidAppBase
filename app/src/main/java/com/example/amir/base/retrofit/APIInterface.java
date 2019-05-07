package com.example.amir.base.retrofit;

import com.example.amir.base.MVVM.models.Film;
import com.example.amir.base.MVVM.models.StarWars;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

    @GET("Doctors/")
    Call<StarWars> getPeople(@Query("format") String format);

    @GET
    Call<Film> getFilmData(@Url String url, @Query("format") String format);

}
