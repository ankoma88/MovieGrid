package com.ankoma88.app.moviegrid.api;

import com.ankoma88.app.moviegrid.mvp.models.GetMoviesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String API_KEY = "ebea8cfca72fdff8d2624ad7bbf78e4c";
    String POSTER_ENDPOINT = "http://image.tmdb.org/t/p/w342";



    @GET("3/movie/now_playing")
    Call<GetMoviesResult> queryGetMovies(@Query("api_key") String apiKey,
                                         @Query("page") int page);
}
