package com.example.amir.base.dagger.module;

import android.content.Context;

import com.example.amir.base.dagger.qualifier.ApplicationContext;
import com.example.amir.base.dagger.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
