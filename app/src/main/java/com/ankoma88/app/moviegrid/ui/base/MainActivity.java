package com.ankoma88.app.moviegrid.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.ankoma88.app.moviegrid.R;
import com.ankoma88.app.moviegrid.ui.MoviesFragment;


public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private MoviesFragment moviesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View toolbarView = mInflater.inflate(R.layout.toolbar_my_account, null);
        toolbar.addView(toolbarView);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            updateFragment();
        }
    }

    private void updateFragment() {
        if (moviesFragment == null) {
            moviesFragment = MoviesFragment.newInstance();
        }
        showRootFragment(moviesFragment);

    }

    public void showRootFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment, null)
                .commit();
    }

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }


}
