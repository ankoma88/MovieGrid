package com.ankoma88.app.moviegrid.ui.adapters;


import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ankoma88.app.moviegrid.R;
import com.ankoma88.app.moviegrid.mvp.models.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ankoma88.app.moviegrid.util.Utils.*;
import static com.ankoma88.app.moviegrid.util.Utils.dateFormatDefault;

public class MovieInfoAdapter extends RecyclerView.Adapter{
    private static final String TAG = MovieInfoAdapter.class.getSimpleName();

    private List<Movie> movies;

    public MovieInfoAdapter(List<Movie> movies) {
        this.movies = movies;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_info, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder vh = (MovieViewHolder) holder;
        if (movies.get(position) != null) {
            bind(vh, movies.get(position));
        }
    }

    private void bind(MovieViewHolder vh, Movie movie) {
        if (movie.getId() == null) {
            return;
        }
        vh.ivPoster.setImageURI(Uri.parse(vh.itemView.getContext().getString(R.string.poster_endpoint) + movie.getPosterPath()));
        vh.tvScore.setText(String.valueOf(movie.getVoteCount()));
        vh.tvRating.setText(String.valueOf(movie.getVoteAverage()));
        vh.tvTitle.setText(movie.getTitle());
        vh.tvDescription.setText(movie.getOverview());
        try {
            vh.tvReleaseDate.setText(dateFormat.format(dateFormatDefault.parse(movie.getReleaseDate())));
        } catch (ParseException e) {
            vh.tvReleaseDate.setText(movie.getReleaseDate());
        }
    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llRoot) LinearLayout llRoot;
        @BindView(R.id.ivPoster) SimpleDraweeView ivPoster;
        @BindView(R.id.tvScore) TextView tvScore;
        @BindView(R.id.tvRating) TextView tvRating;
        @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
        @BindView(R.id.tvTitle) TextView  tvTitle;
        @BindView(R.id.tvDescription) TextView tvDescription;


        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
