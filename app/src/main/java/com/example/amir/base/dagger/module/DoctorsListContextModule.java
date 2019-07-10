package com.example.amir.base.dagger.module;

import android.content.Context;

import com.example.amir.base.MVVM.views.DoctorsList;
import com.example.amir.base.MVVM.views.MainActivity;
import com.example.amir.base.dagger.qualifier.ActivityContext;
import com.example.amir.base.dagger.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DoctorsListContextModule {
    private DoctorsList doctorsList;

    public Context context;

    public DoctorsListContextModule(DoctorsList doctorsList) {
        this.doctorsList = doctorsList;
        context = doctorsList;
    }

    @Provides
    @ActivityScope
    public DoctorsList providesDoctorsListActivity() {
        return doctorsList;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }



}
