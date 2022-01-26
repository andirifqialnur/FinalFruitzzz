package com.example.fruitzzz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecipePageAdapter extends RecyclerView.Adapter<RecipePageAdapter.RecipePageViewHolder> {

    List<RecipePages> mRecipePages;
    Context mContext;

    public RecipePageAdapter(Context context, List<RecipePages> DataRecipePage){
        mRecipePages = DataRecipePage;
        mContext = context;
    }

    @NonNull
    @Override
    public RecipePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_recipe, parent, false);
        RecipePageViewHolder recipePageViewHolder = new RecipePageViewHolder(view);
        return recipePageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipePageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Titlee.setText(mRecipePages.get(position).getTitle());
        holder.Subtitlee.setText(mRecipePages.get(position).getSubTitle());
        holder.Categoryy.setText(mRecipePages.get(position).getCategory());
        holder.Caloriess.setText(mRecipePages.get(position).getCalories());
        holder.Timee.setText(mRecipePages.get(position).getTime());
        Glide.with(mContext).asBitmap().load(mRecipePages.get(position).getImage()).into(holder.imageView);
        Glide.with(mContext).asBitmap().load(mRecipePages.get(position).getBtnIntent()).into(holder.BtnIntentt);
        holder.BtnIntentt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mRecipePages.get(position).getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipePages.size();
    }

    public class RecipePageViewHolder  extends RecyclerView.ViewHolder{
        ImageView imageView, BtnIntentt;
        TextView Titlee, Subtitlee, Categoryy, Caloriess, Timee;

        public RecipePageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            Titlee = itemView.findViewById(R.id.title);
            Subtitlee = itemView.findViewById(R.id.subtitle);
            Categoryy = itemView.findViewById(R.id.category);
            Caloriess = itemView.findViewById(R.id.calories);
            Timee = itemView.findViewById(R.id.time);
            BtnIntentt = itemView.findViewById(R.id.btnIntent);
        }
    }
}
