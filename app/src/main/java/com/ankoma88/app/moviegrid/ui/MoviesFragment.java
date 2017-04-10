package com.ankoma88.app.moviegrid.ui;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ankoma88.app.moviegrid.R;
import com.ankoma88.app.moviegrid.mvp.models.Movie;
import com.ankoma88.app.moviegrid.mvp.presenters.MoviesPresenter;
import com.ankoma88.app.moviegrid.mvp.views.MoviesView;
import com.ankoma88.app.moviegrid.ui.adapters.MoviesAdapter;
import com.ankoma88.app.moviegrid.ui.base.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ankoma88.app.moviegrid.mvp.presenters.MoviesPresenter.LAST_PAGE;

public class MoviesFragment extends BaseFragment implements MoviesView, MoviesAdapter.FragmentCallback {
    public static final String TAG = MoviesFragment.class.getSimpleName();

    @InjectPresenter MoviesPresenter presenter;

    @BindView(R.id.rvMovies) protected RecyclerView rvMovies;

    private MoviesAdapter adapter;

    public MoviesFragment() {
    }

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onAttach();
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showToolbar(true);
        setToolbarTitle(getString(R.string.movies));
        setupRecyclerView();

        if (adapter == null) {
            presenter.requestMovies(getString(R.string.api_key));
        }
    }

    private void setupRecyclerView() {
        rvMovies.setHasFixedSize(true);

        final GridLayoutManager gridLayoutManager = getActivity().getResources()
                .getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ?
                new GridLayoutManager(getContext(), 2) :
                new GridLayoutManager(getContext(), 4);


        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(adapter.getItemViewType(position)){
                    case MoviesAdapter.HEADER:
                        return gridLayoutManager.getSpanCount();

                    case MoviesAdapter.MOVIE:
                        return 1;

                    case MoviesAdapter.FOOTER:
                        return gridLayoutManager.getSpanCount();

                    default:
                        return 1;
                }
            }
        });

        rvMovies.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void setAdapter(ArrayList<Movie> movies, int page) {
        adapter = new MoviesAdapter(this, movies, page);
        rvMovies.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(ArrayList<Movie> moviesList, int moviePosition) {
        showFragment(MovieInfoFragment.newInstance(moviesList, moviePosition));
    }

    @Override
    public void onFirstClick() {
        presenter.setPage(1);
        presenter.requestMovies(getString(R.string.api_key));
    }

    @Override
    public void onPrevClick() {
        presenter.decrementPage();
        presenter.requestMovies(getString(R.string.api_key));
    }

    @Override
    public void onNextClick() {
        presenter.incrementPage();
        presenter.requestMovies(getString(R.string.api_key));
    }

    @Override
    public void onLastClick() {
        presenter.setPage(LAST_PAGE);
        presenter.requestMovies(getString(R.string.api_key));
    }

}
