package com.ankoma88.app.moviegrid.di;


import android.app.Application;


import com.ankoma88.app.moviegrid.App;
import com.ankoma88.app.moviegrid.mvp.presenters.MoviesPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiServiceModule.class})
public final class AppModule {
    private final App app;

    AppModule(App app) {
        this.app = app;
    }

    @Provides @Singleton
    Application application() {
        return app;
    }

    @Provides @Singleton
    public MoviesPresenter provideMoviesPresenter() {
        return new MoviesPresenter();
    }

}
