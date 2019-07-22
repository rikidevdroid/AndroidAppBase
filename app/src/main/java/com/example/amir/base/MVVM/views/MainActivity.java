package com.example.amir.base.MVVM.views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.amir.base.MVVM.models.ProfileResponse;
import com.example.amir.base.MyApplication;
import com.example.amir.base.R;

import com.example.amir.base.dagger.component.ApplicationComponent;
import com.example.amir.base.dagger.component.DaggerMainActivityComponent;
import com.example.amir.base.dagger.component.MainActivityComponent;

import com.example.amir.base.retrofit.APIInterface;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    MainActivityComponent mainActivityComponent;

    @Inject
    public APIInterface apiInterface;

    @BindView(R.id.userName)
    TextView userName;

    @BindView(R.id.id_doctors_list_btn)
    Button doctorsListButton;

    private SharedPreferences sharedPreferences;

    private String userID;
    private String token;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        userID = getIntent().getSerializableExtra("userId").toString();
        token = getIntent().getSerializableExtra("token").toString();


        apiInterface.getProfile(userID, "bearer " + token).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().result != null) {

                        userName.setText(response.body().result.profile.firstName + " " + response.body().result.profile.lastName);

                        editor.putString("patientsPhoneNumber" , response.body().result.profile.phoneNumber);
                        // false on logout
                        editor.apply();

                        //userName.setText(response.body().result.profile.subscriberCards.get(0).state);
                    } else
                        userName.setText("Profile is Null");
                } else
                    userName.setText("Error");
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                userName.setText(t.getMessage());

            }
        });


        doctorsListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoctorsList.class);
                startActivity(intent);
                //finish();
            }
        });


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}