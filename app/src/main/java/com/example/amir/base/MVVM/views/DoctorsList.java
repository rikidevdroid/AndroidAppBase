package com.example.amir.base.MVVM.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.base.MVVM.models.DoctorsResponse;
import com.example.amir.base.MVVM.models.OldDoctors;
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
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsList extends AppCompatActivity implements RecyclerViewAdapter.ClickListener , TextWatcher {


    private SharedPreferences sharedPreferences;

    private Timer timer;

    @BindView(R.id.id_doctors_list)
    public RecyclerView recyclerView;

    @BindView(R.id.id_pg)
    public ProgressBar progressBar;

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

    @BindView(R.id.id_doctors_search)
    public EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);
        ButterKnife.bind(this);


        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        doctorsListActivityComponent = DaggerDoctorsListActivityComponent.builder()
                .doctorsListContextModule(new DoctorsListContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        doctorsListActivityComponent.inject(this);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        //recyclerView.setHasFixedSize(true);
        search.addTextChangedListener(this);
        search.setText("777");


//        apiInterface.getDoctors("specialty,subscriberNumber,firstName,lastName,imagePath").enqueue(new Callback<DoctorsResponse>() {
//            @Override
//            public void onResponse(Call<DoctorsResponse> call, Response<DoctorsResponse> response) {
//                if (response.isSuccessful()){
//
//                    if(response.body()!=null){
//
//                        //test.setText(response.body().result.doctors.get(0).firstName);
//                        populateRecyclerView(response.body().result.doctors);
//
//                    }
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DoctorsResponse> call, Throwable t) {
//
//            }
//        });


//        apiInterface.getDoctors("firstName,title","7562").enqueue(new Callback<OldDoctors>() {
//            @Override
//            public void onResponse(Call<OldDoctors> call, Response<OldDoctors> response) {
//                if(response.isSuccessful()){
//                    if (response.body() != null) {
//                        //populateRecyclerView(response.body().firstName);
//                    }
//                    else
//                        Toast.makeText(mContext, "NULLLLLLLLLLLLLLLLLLL", Toast.LENGTH_LONG).show();
//
//                    Toast.makeText(mContext, "AAAAAAAAAAAAAAAAAA", Toast.LENGTH_LONG).show();
//                }
//                else
//                    Toast.makeText(mContext, "Ridi", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<OldDoctors> call, Throwable t) {
//
//                Toast.makeText(mContext, t.getCause().getMessage(), Toast.LENGTH_LONG).show();
//                Log.v("000000000000" , t.getCause().getMessage());
//
//
//
//            }
//
//    });


    }


    private void populateRecyclerView(List<DoctorsResponse.Result.Doctor> response) {
        recyclerView.removeAllViewsInLayout();
        recyclerViewAdapter.setData(response);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void launchIntent(String url) {
        Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mContext, DoctorsList.class).putExtra("url", url));
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {



    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (timer != null) {
            timer.cancel();
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

        // user typed: start the timer
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(search.getText().toString().isEmpty()){

                    apiInterface.getDoctors("specialty,subscriberNumber,firstName,lastName,imagePath,currentlyAvailable").enqueue(new Callback<DoctorsResponse>() {
                        @Override
                        public void onResponse(Call<DoctorsResponse> call, Response<DoctorsResponse> response) {
                            if (response.isSuccessful()){

                                if(response.body()!=null){

                                    //test.setText(response.body().result.doctors.get(0).firstName);
                                    populateRecyclerView(response.body().result.doctors);
                                    progressBar.setVisibility(View.GONE);

                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<DoctorsResponse> call, Throwable t) {

                        }
                    });

                }

                else {

                    apiInterface.getDoctors("specialty,subscriberNumber,firstName,lastName,imagePath",search.getText().toString()).enqueue(new Callback<DoctorsResponse>() {
                        @Override
                        public void onResponse(Call<DoctorsResponse> call, Response<DoctorsResponse> response) {
                            if (response.isSuccessful()){

                                if(response.body()!=null){

                                    //test.setText(response.body().result.doctors.get(0).firstName);
                                    populateRecyclerView(response.body().result.doctors);
                                    progressBar.setVisibility(View.GONE);

                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<DoctorsResponse> call, Throwable t) {

                        }
                    });


                }
            }
        }, 3000);


        progressBar.bringToFront();
        progressBar.setVisibility(View.VISIBLE);



    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

}
