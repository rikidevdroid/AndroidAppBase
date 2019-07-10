package com.example.amir.base.dagger.component;

import android.content.Context;

import com.example.amir.base.MVVM.views.MainActivity;
import com.example.amir.base.dagger.module.AdapterModule;
import com.example.amir.base.dagger.qualifier.ActivityContext;
import com.example.amir.base.dagger.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component( dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);
}