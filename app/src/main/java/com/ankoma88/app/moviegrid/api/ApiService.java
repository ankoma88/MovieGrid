package com.ankoma88.app.moviegrid.api;


import com.ankoma88.app.moviegrid.mvp.models.GetMoviesResult;
import retrofit2.Call;

public class ApiService {

    private Api api;

    public ApiService(Api api) {
        this.api = api;
    }

    public Call<GetMoviesResult> queryGetMovies(String apiKey, int page) {
        return api.queryGetMovies(apiKey, page);
    }
}

























