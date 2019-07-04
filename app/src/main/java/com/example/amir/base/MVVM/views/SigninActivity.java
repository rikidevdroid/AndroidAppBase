package com.example.amir.base.MVVM.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.amir.base.dagger.component.DaggerDetailActivityComponent;
import com.example.amir.base.dagger.component.DaggerSigninActivityComponent;
import com.example.amir.base.dagger.component.SigninActivityComponent;
import com.example.amir.base.retrofit.APIInterface;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends Activity {

    SigninActivityComponent signinActivityComponent;

    @Inject
    public APIInterface apiInterface;

    @BindView(R.id.id_userName)
    EditText userNameEditText;

    @BindView(R.id.id_password)
    EditText passwordEditText;

    @BindView(R.id.id_loginButton)
    Button loginButton;


    private String userName;
    private String password;
    //private String grantType = "password";

    private Oauth2 auth;

    private String accessToken;
    private String id;

    private JWT jwt;
    private Claim claim;

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



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userNameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                apiInterface.login(userName,password,getString(R.string.grant_type)).enqueue(new Callback<Oauth2>() {
                    @Override
                    public void onResponse(Call<Oauth2> call, Response<Oauth2> response) {
                        if(response.isSuccessful()) {

                            accessToken = response.body().accessToken;
                            jwt = new JWT(accessToken);
                            claim = jwt.getClaim("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name");
                            id = claim.asString();
                            Toast.makeText(getBaseContext(), id, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Oauth2> call, Throwable t) {

                    }
                });
            }
        });

    }
}
