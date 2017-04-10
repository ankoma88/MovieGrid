package com.ankoma88.app.moviegrid.mvp.views;


import com.ankoma88.app.moviegrid.mvp.models.Movie;
import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

public interface MoviesView extends MvpView {

    void setAdapter(ArrayList<Movie> movies, int page);

}
