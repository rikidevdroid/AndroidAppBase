package com.example.amir.base.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.base.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment {

    private View view;

    @BindView(R.id.id_signin_page)
    public TextView signIn;

    @BindView(R.id.id_getsmscode_btn)
    public TextView getSMS;



    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup,
                container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                SignInFragment fragment3 = new SignInFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment3);
//provide the fragment ID of your first fragment which you have given in
//fragment_layout_example.xml file in place of first argument
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        getSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                VerifyCodeFragment verifyCodeFragment= new VerifyCodeFragment();
                fragmentTransaction.replace(R.id.fragment_container, verifyCodeFragment);
//provide the fragment ID of your first fragment which you have given in
//fragment_layout_example.xml file in place of first argument
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



    }


}