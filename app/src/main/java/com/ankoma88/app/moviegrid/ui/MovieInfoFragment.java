package com.ankoma88.app.moviegrid.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ankoma88.app.moviegrid.R;
import com.ankoma88.app.moviegrid.mvp.models.Movie;
import com.ankoma88.app.moviegrid.ui.adapters.MovieInfoAdapter;
import com.ankoma88.app.moviegrid.ui.base.BaseFragment;
import com.ankoma88.app.moviegrid.ui.views.CustomLinearLayoutManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.fresco.processors.BlurPostprocessor;

public class MovieInfoFragment extends BaseFragment {
    public static final String TAG = MovieInfoFragment.class.getSimpleName();
    private static final String EXTRA_MOVIES = "extraMovies";
    private static final String EXTRA_POSITION = "extraPosition";

    @BindView(R.id.llRoot)
    protected LinearLayout llRoot;
    @BindView(R.id.ivBlur)
    protected SimpleDraweeView ivBlur;
    @BindView(R.id.rvMovieInfos)
    protected RecyclerView rvMovieInfos;
    @BindView(R.id.llBack)
    protected LinearLayout llBack;
    @BindView(R.id.llNext)
    protected LinearLayout llNext;
    @BindView(R.id.ivArrowBack)
    protected ImageView ivArrowBack;
    @BindView(R.id.ivArrowNext)
    protected ImageView ivArrowNext;

    private ArrayList<Movie> movies;

    public MovieInfoFragment() {
    }

    public static Fragment newInstance(ArrayList<Movie> movies, int position) {
        MovieInfoFragment fragment = new MovieInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_MOVIES, Parcels.wrap(movies));
        bundle.putInt(EXTRA_POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (movies == null) {
            movies = Parcels.unwrap(getArguments().getParcelable(EXTRA_MOVIES));
            movies.remove(0);
            movies.remove(movies.size() - 1);
        }
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showToolbar(false);
        rvMovieInfos.setHasFixedSize(true);
        rvMovieInfos.setLayoutManager(new CustomLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMovieInfos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                adjustNavButtons();
                ((CustomLinearLayoutManager)rvMovieInfos.getLayoutManager()).setScrollEnabled(false);
                blurBackgroundWithPoster(movies);
            }
        });
        setAdapter(movies);
        rvMovieInfos.scrollToPosition(getArguments().getInt(EXTRA_POSITION));

        adjustNavButtons();

        ((CustomLinearLayoutManager)rvMovieInfos.getLayoutManager()).setScrollEnabled(false);
    }

    private void adjustNavButtons() {
        llBack.setVisibility(getCurrentPosition() != 0 ? View.VISIBLE : View.GONE);
        llNext.setVisibility(getCurrentPosition() == movies.size() - 1 ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.llBack)
    public void onBackClick() {
        ((CustomLinearLayoutManager)rvMovieInfos.getLayoutManager()).setScrollEnabled(true);
        rvMovieInfos.scrollToPosition(getCurrentPosition() - 1 <= 0 ? 0 : getCurrentPosition() - 1);
    }

    @OnClick(R.id.llNext)
    public void onNextClick() {
        ((CustomLinearLayoutManager)rvMovieInfos.getLayoutManager()).setScrollEnabled(true);
        rvMovieInfos.scrollToPosition(getCurrentPosition() + 1);
    }

    private int getCurrentPosition() {
        int firstCompletelyVisibleItemPosition = ((LinearLayoutManager) rvMovieInfos
                .getLayoutManager()).findFirstVisibleItemPosition();
        if (firstCompletelyVisibleItemPosition == -1) {
            firstCompletelyVisibleItemPosition = 0;
        }
        return firstCompletelyVisibleItemPosition;
    }

    public void setAdapter(List<Movie> movies) {
        rvMovieInfos.setAdapter(new MovieInfoAdapter(movies));
    }

    private void blurBackgroundWithPoster(List<Movie> movies) {
        ImageRequest request =
                ImageRequestBuilder.newBuilderWithSource(
                        Uri.parse(getContext().getString(R.string.poster_endpoint) + movies.get(getCurrentPosition()).getPosterPath()))
                        .setPostprocessor(new BlurPostprocessor(getContext(), 25))
                        .build();
        PipelineDraweeController controller =
                (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(ivBlur.getController())
                        .build();
        ivBlur.setController(controller);
    }


}
