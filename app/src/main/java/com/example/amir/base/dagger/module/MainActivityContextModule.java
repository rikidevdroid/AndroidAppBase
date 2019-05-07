package com.example.amir.base.dagger.module;

import android.content.Context;

import com.example.amir.base.MVVM.views.MainActivity;
import com.example.amir.base.dagger.qualifier.ActivityContext;
import com.example.amir.base.dagger.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}