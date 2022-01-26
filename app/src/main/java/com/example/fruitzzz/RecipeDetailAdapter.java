package com.example.fruitzzz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RecipeDetailAdapter extends ArrayAdapter<HomeRecipeDetail> {

    public RecipeDetailAdapter(Context context, ArrayList<HomeRecipeDetail> recipeDetailArrayList) {
        super(context, R.layout.activity_recipe_details, recipeDetailArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        HomeRecipeDetail homeRecipeDetail = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_recipe_details, parent,false);
        }

        ImageView image_detail = convertView.findViewById(R.id.image_detail);
        TextView title = convertView.findViewById(R.id.titles);
        TextView desc = convertView.findViewById(R.id.desc);
        TextView calorie = convertView.findViewById(R.id.calor_total);
        TextView cooked = convertView.findViewById(R.id.time_total);
        TextView amount = convertView.findViewById(R.id.amount_total);
        TextView ingredient = convertView.findViewById(R.id.ingredients);
        TextView step = convertView.findViewById(R.id.steps);

        image_detail.setImageResource(homeRecipeDetail.imageDetail);
        title.setText(homeRecipeDetail.title);
        desc.setText(homeRecipeDetail.desc);
        calorie.setText(homeRecipeDetail.calorie);
        cooked.setText(homeRecipeDetail.cook);
        amount.setText(homeRecipeDetail.amount);
        ingredient.setText(homeRecipeDetail.ingredient);
        step.setText(homeRecipeDetail.step);

        return convertView;
    }
}
