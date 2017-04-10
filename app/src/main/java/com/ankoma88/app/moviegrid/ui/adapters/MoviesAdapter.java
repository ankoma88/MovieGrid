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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter{
    private static final String TAG = MoviesAdapter.class.getSimpleName();
    public static final int HEADER = 1;
    public static final int MOVIE = 2;
    public static final int FOOTER = 3;


    private FragmentCallback fragmentCallback;
    private ArrayList<Movie> items;
    private int page;

    public MoviesAdapter(FragmentCallback fragmentCallback, ArrayList<Movie> movies, int page) {
        this.fragmentCallback = fragmentCallback;
        this.page = page;
        items = new ArrayList<>();
        items.add(new Movie());
        items.addAll(movies);
        items.add(new Movie());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else if (position == getItemCount() - 1) {
            return FOOTER;
        } else return MOVIE;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false));
        } else if (viewType == FOOTER) {
            return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false));
        } else {
            return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(holder.getAdapterPosition()) == MOVIE) {
                bindMovie((MovieViewHolder) holder, items.get(holder.getAdapterPosition()));
        } else if (getItemViewType(holder.getAdapterPosition()) == FOOTER) {
            bindFooter((FooterViewHolder) holder);
        }
    }

    private void bindMovie(MovieViewHolder vh, Movie movie) {
        vh.itemView.setOnClickListener(view -> fragmentCallback.onItemClicked(items, items.indexOf(movie) - 1));
        vh.ivPoster.setImageURI(Uri.parse(vh.itemView.getContext().getString(R.string.poster_endpoint) + movie.getPosterPath()));
    }

    private void bindFooter(FooterViewHolder vh) {
        vh.llFirst.setOnClickListener(v -> fragmentCallback.onFirstClick());
        vh.llPrev.setOnClickListener(v -> fragmentCallback.onPrevClick());
        vh.llNext.setOnClickListener(v -> fragmentCallback.onNextClick());
        vh.llLast.setOnClickListener(v -> fragmentCallback.onLastClick());
        vh.tvCurrentPage.setText(String.valueOf(page));
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPoster)
        SimpleDraweeView ivPoster;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llFirst) LinearLayout llFirst;
        @BindView(R.id.llPrev) LinearLayout llPrev;
        @BindView(R.id.llNext) LinearLayout llNext;
        @BindView(R.id.llLast) LinearLayout llLast;
        @BindView(R.id.tvCurrentPage) TextView tvCurrentPage;

        FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface FragmentCallback {
        void onItemClicked(ArrayList<Movie> moviesList,  int moviePosition);

        void onFirstClick();

        void onPrevClick();

        void onNextClick();

        void onLastClick();
    }

}
