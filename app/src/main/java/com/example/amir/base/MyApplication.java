package com.example.amir.base;

import android.app.Activity;
import android.app.Application;

import com.example.amir.base.dagger.component.ApplicationComponent;
import com.example.amir.base.dagger.component.DaggerApplicationComponent;
import com.example.amir.base.dagger.module.ContextModule;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}