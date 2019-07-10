package com.example.amir.base.MVVM.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.amir.base.MVVM.models.Doctors;
import com.example.amir.base.MyApplication;
import com.example.amir.base.R;
import com.example.amir.base.adapters.RecyclerViewAdapter;
import com.example.amir.base.dagger.component.ApplicationComponent;

import com.example.amir.base.dagger.component.DaggerDoctorsListActivityComponent;
import com.example.amir.base.dagger.component.DoctorsListActivityComponent;
import com.example.amir.base.dagger.module.DoctorsListContextModule;
import com.example.amir.base.dagger.qualifier.ActivityContext;
import com.example.amir.base.dagger.qualifier.ApplicationContext;
import com.example.amir.base.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsList extends AppCompatActivity implements RecyclerViewAdapter.ClickListener {


    @BindView(R.id.id_doctors_list)
    public RecyclerView recyclerView;


    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    DoctorsListActivityComponent doctorsListActivityComponent;

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
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);


        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        doctorsListActivityComponent = DaggerDoctorsListActivityComponent.builder()
                .doctorsListContextModule(new DoctorsListContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        doctorsListActivityComponent.inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(recyclerViewAdapter);

        apiInterface.getDoctors("firstName,title","7562").enqueue(new Callback<Doctors>() {
            @Override
            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        //populateRecyclerView(response.body().firstName);
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


    }


    private void populateRecyclerView(List<Doctors.Doctor> response) {
        recyclerViewAdapter.setData(response);
    }

    @Override
    public void launchIntent(String url) {
        Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mContext, DoctorsList.class).putExtra("url", url));
    }


}
