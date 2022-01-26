package com.example.fruitzzz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsPageAdapter extends RecyclerView.Adapter<NewsPageAdapter.NewsPageHolder> {

    Context context;
    ArrayList<NewsPages> newsPagesArrayList;

    public NewsPageAdapter(Context context, ArrayList<NewsPages> newsPages) {
        this.context = context;
        this.newsPagesArrayList = newsPages;
    }

    @NonNull
    @Override
    public NewsPageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_lay, null, false);
        return new NewsPageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsPageAdapter.NewsPageHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("url", newsPagesArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
        holder.Heading.setText(newsPagesArrayList.get(position).getTitle());
        holder.Content.setText(newsPagesArrayList.get(position).getDescription());
        holder.Author.setText(newsPagesArrayList.get(position).getAuthor());
        holder.Time.setText("Published At:-" + newsPagesArrayList.get(position).getPublishedAt());
        Glide.with(context).load(newsPagesArrayList.get(position).getUrlToImage()).into(holder.ImageView);
    }

    @Override
    public int getItemCount() {
        return newsPagesArrayList.size();
    }

    public class NewsPageHolder extends RecyclerView.ViewHolder {

        TextView Heading, Content, Author, Time;
        CardView CardView;
        ImageView ImageView;

        public NewsPageHolder(@NonNull View itemView) {
            super(itemView);

            Heading = itemView.findViewById(R.id.heading);
            Content = itemView.findViewById(R.id.content);
            Author = itemView.findViewById(R.id.author);
            Time = itemView.findViewById(R.id.time);
            CardView = itemView.findViewById(R.id.CardView);
            ImageView = itemView.findViewById(R.id.imageView);
        }
    }
}
