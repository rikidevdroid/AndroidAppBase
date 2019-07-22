package com.example.amir.base.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.amir.base.MVVM.models.Oauth2;
import com.example.amir.base.MVVM.views.MainActivity;
import com.example.amir.base.R;
import com.example.amir.base.retrofit.APIInterface;
import com.example.amir.base.utils.RetrofitClientInstance;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInFragment extends Fragment {

    private View view;

    private String userName;
    private String password;
    //private String grantType = "password";

    private Oauth2 auth;

    private String accessToken;
    private String id;

    private JWT jwt;
    private Claim claim;



    @Inject
    APIInterface apiInterface;

    @BindView(R.id.id_signup_page)
    public TextView signUp;

    @BindView(R.id.id_userName)
    public EditText userNameEditText;

    @BindView(R.id.id_password)
    public EditText passwordEditText;

    @BindView(R.id.id_loginButton)
    public Button loginButton;


    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signin,
                container, false);
        ButterKnife.bind(this, view);

        apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                SignUpFragment fragment3 = new SignUpFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment3);
//provide the fragment ID of your first fragment which you have given in
//fragment_layout_example.xml file in place of first argument
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


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
                            claim = jwt.getClaim(getString(R.string.jwt_id_key));
                            id = claim.asString();

                            Intent intent = new Intent(getContext(), MainActivity.class);
                            intent.putExtra("userId" , id);
                            intent.putExtra("token" , accessToken);



                            startActivity(intent);
                            //getActivity().finish();

                            //Toast.makeText(getBaseContext(), id, Toast.LENGTH_LONG).show();
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

