package com.example.amir.base.MVVM.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.amir.base.MVVM.models.Doctors;
import com.example.amir.base.MyApplication;
import com.example.amir.base.R;
import com.example.amir.base.adapters.RecyclerViewAdapter;
import com.example.amir.base.dagger.component.ApplicationComponent;
import com.example.amir.base.dagger.component.DaggerMainActivityComponent;
import com.example.amir.base.dagger.component.MainActivityComponent;
import com.example.amir.base.dagger.module.MainActivityContextModule;
import com.example.amir.base.dagger.qualifier.ActivityContext;
import com.example.amir.base.dagger.qualifier.ApplicationContext;
import com.example.amir.base.retrofit.APIInterface;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        apiInterface.getDoctors("firstName,title","7562").enqueue(new Callback<Doctors>() {
            @Override
            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        populateRecyclerView2(response.body().result);
                    }
                    else
                        Toast.makeText(mContext, "NULLLLLLLLLLLLLLLLLLL", Toast.LENGTH_LONG).show();

                    Toast.makeText(mContext, "AAAAAAAAAAAAAAAAAA", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(mContext, "Ridi", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Doctors> call, Throwable t) {

                Toast.makeText(mContext, t.getCause().getMessage(), Toast.LENGTH_LONG).show();
                Log.v("000000000000" , t.getCause().getMessage());



            }

    });

//        apiInterface.getPeople("json").enqueue(new Callback<StarWars>() {
//            @Override
//            public void onResponse(Call<StarWars> call, Response<StarWars> response) {
//                if(response.isSuccessful())
//                populateRecyclerView(response.body().results);
//                else
//                    Toast.makeText(mContext, "Ridi", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<StarWars> call, Throwable t) {
//
//            }
//        });
    }

//    private void populateRecyclerView(List<StarWars.People> response) {
//        recyclerViewAdapter.setData(response);
//    }

    private void populateRecyclerView2(List<Doctors.Doctor> response) {
        recyclerViewAdapter.setData(response);
    }

    @Override
    public void launchIntent(String url) {
        Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(activityContext, DetailActivity.class).putExtra("url", url));
    }
}



