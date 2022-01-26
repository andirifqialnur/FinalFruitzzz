package com.example.fruitzzz;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsAPIUtiities {
    private static Retrofit retrofit=null;

    public static NewsAPIInterface getNewsAPIInterface(){
        if (retrofit==null) {
            retrofit=new Retrofit.Builder().baseUrl(NewsAPIInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(NewsAPIInterface.class);
    }
}