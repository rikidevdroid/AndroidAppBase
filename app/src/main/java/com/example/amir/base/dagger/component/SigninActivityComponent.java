package com.example.amir.base.dagger.component;


import android.content.Context;


import com.example.amir.base.MVVM.views.SigninActivity;
import com.example.amir.base.dagger.qualifier.ActivityContext;
import com.example.amir.base.dagger.scopes.ActivityScope;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
@ActivityScope
public interface SigninActivityComponent {

    void inject(SigninActivity signinActivity);
}
