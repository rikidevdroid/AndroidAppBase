package com.example.amir.base.MVVM.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.amir.base.MVVM.models.OldDoctors;
import com.example.amir.base.repositories.DoctorRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<OldDoctors>> doctorsMutableLiveData;
    private DoctorRepository doctorRepository;
    private MutableLiveData<Boolean> dataIsUpdating = new MutableLiveData<>();


    public void init(){
        if(doctorsMutableLiveData != null)
            return;

//        doctorRepository.getInstance();
//        doctorRepository.getDoctors();

    }

    public LiveData<List<OldDoctors>> getDoctors(){
        return doctorsMutableLiveData;
    }
}
