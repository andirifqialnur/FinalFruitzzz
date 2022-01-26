package com.example.fruitzzz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPage extends Fragment {

    String api = "a0034ff0033e4b279e3badcae7af80bb";
    ArrayList<NewsPages> newsPagesArrayList;
    NewsPageAdapter adapter;
    String country = "id";
    private RecyclerView recyclerView;
    private String category = "sport";
    View view;

    public NewsPage() { }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_page, container, false);

        recyclerView = view.findViewById(R.id.news);
        newsPagesArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsPageAdapter(getContext(), newsPagesArrayList);
        recyclerView.setAdapter(adapter);

        findNews();

        return view;
    }

    private void findNews() {
        NewsAPIUtiities.getNewsAPIInterface().getCategoryNews(country, category, 100, api).enqueue(new Callback<NewsMain>() {
            @Override
            public void onResponse(Call<NewsMain> call, Response<NewsMain> response) {
                if (response.isSuccessful()) {
                    newsPagesArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsMain> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}