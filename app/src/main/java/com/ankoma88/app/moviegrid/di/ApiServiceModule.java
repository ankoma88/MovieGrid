package com.ankoma88.app.moviegrid.di;


import com.ankoma88.app.moviegrid.api.Api;
import com.ankoma88.app.moviegrid.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApiModule.class)
public class ApiServiceModule {
    @Provides
    @Singleton
    public ApiService provideApiService(Api api) {
        return new ApiService(api);
    }

}
