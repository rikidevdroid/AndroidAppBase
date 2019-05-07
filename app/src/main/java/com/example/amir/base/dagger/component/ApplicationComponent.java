package com.example.amir.base.dagger.component;

import android.content.Context;

import com.example.amir.base.MyApplication;
import com.example.amir.base.dagger.module.ContextModule;
import com.example.amir.base.dagger.module.RetrofitModule;
import com.example.amir.base.dagger.qualifier.ApplicationContext;
import com.example.amir.base.dagger.scopes.ApplicationScope;
import com.example.amir.base.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public APIInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(MyApplication myApplication);
}
