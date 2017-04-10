package com.ankoma88.app.moviegrid.di;


import com.ankoma88.app.moviegrid.App;
import com.ankoma88.app.moviegrid.mvp.presenters.MoviesPresenter;
import com.ankoma88.app.moviegrid.ui.base.BaseActivity;
import com.ankoma88.app.moviegrid.ui.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(App app);
    void inject(BaseActivity activity);
    void inject(BaseFragment fragment);
    void inject(MoviesPresenter moviesPresenter);

    final class Initializer {
        public static AppComponent init(App app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }

        private Initializer() {}
    }
}
