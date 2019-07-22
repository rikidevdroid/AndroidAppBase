package com.example.amir.base.dagger.component;

import com.example.amir.base.MVVM.views.DoctorDetailsActivity;
import com.example.amir.base.dagger.scopes.ActivityScope;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
@ActivityScope
public interface DoctorDetailsActivityComponent {

    void inject(DoctorDetailsActivity doctorDetails);
}