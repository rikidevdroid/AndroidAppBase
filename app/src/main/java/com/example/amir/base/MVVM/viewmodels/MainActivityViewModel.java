package com.example.amir.base.MVVM.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.amir.base.MVVM.models.Doctors;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Doctors>> doctorsMutableLiveData;


    public void init(){
    }

    public LiveData<List<Doctors>> getDoctors(){
        return doctorsMutableLiveData;
    }
}
