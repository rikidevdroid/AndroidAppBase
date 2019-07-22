package com.example.amir.base.MVVM.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.amir.base.MVVM.models.Oauth2;
import com.example.amir.base.MyApplication;
import com.example.amir.base.R;
import com.example.amir.base.dagger.component.ApplicationComponent;

import com.example.amir.base.dagger.component.DaggerSigninActivityComponent;
import com.example.amir.base.dagger.component.SigninActivityComponent;
import com.example.amir.base.fragments.SignInFragment;
import com.example.amir.base.retrofit.APIInterface;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends FragmentActivity {

    SigninActivityComponent signinActivityComponent;

    @Inject
    public APIInterface apiInterface;

//    @BindView(R.id.id_userName)
//    EditText userNameEditText;
//
//    @BindView(R.id.id_password)
//    EditText passwordEditText;
//
//    @BindView(R.id.id_loginButton)
//    Button loginButton;


    private String userName;
    private String password;
    //private String grantType = "password";

    private Oauth2 auth;

    private String accessToken;
    private String id;

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    private JWT jwt;
    private Claim claim;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_signin);
        ButterKnife.bind(this);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        signinActivityComponent = DaggerSigninActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build();

        signinActivityComponent.inject(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            SignInFragment firstFragment = new SignInFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }



}
