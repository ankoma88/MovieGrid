package com.ankoma88.app.moviegrid.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;


import com.ankoma88.app.moviegrid.App;
import com.ankoma88.app.moviegrid.api.ApiService;
import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

public class BaseActivity extends MvpAppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();

    @Inject protected ApiService apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
    }

}
