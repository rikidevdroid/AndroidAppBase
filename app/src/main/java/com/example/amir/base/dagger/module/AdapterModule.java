package com.example.amir.base.dagger.module;

import com.example.amir.base.MVVM.views.DoctorsList;
import com.example.amir.base.adapters.RecyclerViewAdapter;
import com.example.amir.base.dagger.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DoctorsListContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getStarWarsPeopleLIst(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(DoctorsList doctorsList) {
        return doctorsList;
    }
}