package com.ankoma88.app.moviegrid;


import android.app.Application;

import com.ankoma88.app.moviegrid.di.AppComponent;
import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {
    public static final String TAG = App.class.getSimpleName();

    private static App instance;
    private AppComponent component;


    public static AppComponent getComponent() {
        return instance.component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        instance = this;
        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
        component = AppComponent.Initializer.init(this);
        component.inject(this);
    }

}
