package com.example.fruitzzz;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIInterface {
    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    abstract Call<NewsMain> getNews(
            @Query("country") String country,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apikey);

    @GET("top-headlines")
    Call<NewsMain> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apikey
    );
}
