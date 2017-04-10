package com.ankoma88.app.moviegrid.mvp.presenters;


import com.ankoma88.app.moviegrid.App;
import com.ankoma88.app.moviegrid.api.ApiService;
import com.ankoma88.app.moviegrid.mvp.models.Movie;
import com.ankoma88.app.moviegrid.mvp.views.MoviesView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.ankoma88.app.moviegrid.api.RxUtils.wrapRequest;

@InjectViewState
public class MoviesPresenter extends MvpPresenter<MoviesView> {
    public static final String TAG = MoviesPresenter.class.getSimpleName();

    public static final int LAST_PAGE = 35;
    private int page = 1;

    public void setPage(int page) {
        this.page = page;
    }

    @Inject ApiService apiService;

    public MoviesPresenter() {
        App.getComponent().inject(this);
    }

    public void requestMovies(String apiKey) {
        wrapRequest(apiService.queryGetMovies(apiKey, page)).subscribe(result -> {
            getViewState().setAdapter((ArrayList<Movie>) result.getMovies(), page);
        });
    }

    public void decrementPage() {
        page--;
        if (page < 1) {
            page = 1;
        }
    }

    public void incrementPage() {
        page++;
        if (page > LAST_PAGE) {
            page = LAST_PAGE;
        }
    }
}
