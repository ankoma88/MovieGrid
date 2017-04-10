package com.ankoma88.app.moviegrid.di;


import com.ankoma88.app.moviegrid.api.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
