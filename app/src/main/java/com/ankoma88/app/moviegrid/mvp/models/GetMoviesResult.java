package com.ankoma88.app.moviegrid.mvp.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMoviesResult {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "GetMoviesResult{" +
                "page=" + page +
                ", movies=" + movies +
                ", dates=" + dates +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
