package com.example.fruitzzz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeRecipeAdapter extends RecyclerView.Adapter<HomeRecipeAdapter.RecipeViewHolder> {
    List<HomeRecipe> mData;
    Context mContext;

    public HomeRecipeAdapter(Context context, List<HomeRecipe> Data) {
        mData = Data;
        mContext = context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_lay, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Titlee.setText(mData.get(position).getTitle());
        holder.Subtitlee.setText(mData.get(position).getSubTitle());
        holder.Caloriess.setText(mData.get(position).getCalories());
        holder.Timee.setText(mData.get(position).getTime());
        holder.Amountt.setText(mData.get(position).getAmount());
        Glide.with(mContext).asBitmap().load(mData.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView Titlee, Subtitlee, Caloriess, Timee, Amountt;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            Titlee = itemView.findViewById(R.id.title);
            Subtitlee = itemView.findViewById(R.id.subtitle);
            Caloriess = itemView.findViewById(R.id.calories);
            Timee = itemView.findViewById(R.id.time);
            Amountt = itemView.findViewById(R.id.amount);
        }
    }
}