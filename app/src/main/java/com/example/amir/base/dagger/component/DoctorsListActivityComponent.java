package com.example.amir.base.dagger.component;

import android.content.Context;

import com.example.amir.base.MVVM.views.DoctorsList;
import com.example.amir.base.dagger.module.AdapterModule;
import com.example.amir.base.dagger.qualifier.ActivityContext;
import com.example.amir.base.dagger.scopes.ActivityScope;

import dagger.Component;

@Component(modules = AdapterModule.class , dependencies = ApplicationComponent.class)
@ActivityScope
public interface DoctorsListActivityComponent {

    @ActivityContext
    Context getContext();

    void inject(DoctorsList doctorsListActivity);
}