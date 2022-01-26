package com.example.fruitzzz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipePage extends Fragment {

    View view;

    RecyclerView GridRecycle;

    private List<RecipePages> listRecipePages;

    public RecipePage() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_recipe_page, container, false);

        GridRecycle = view.findViewById(R.id.gridRecipe);

        RecipePageAdapter recipePageAdapter = new RecipePageAdapter(getContext(), listRecipePages);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2, GridLayoutManager.VERTICAL, false);
        GridRecycle.setLayoutManager(gridLayoutManager);
        GridRecycle.setAdapter(recipePageAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listRecipePages = new ArrayList<>();
        listRecipePages.add(new RecipePages("Salad Buah", "Lorem Ipsum dolor amet gihan", "Salad", "33.3kCal", "25 min", R.drawable.recipe1, R.drawable.buttob60));
        listRecipePages.add(new RecipePages("Mango Pudding", "Lorem Ipsum dolor amet gihan", "Pudding", "12.4kCal", "20 min", R.drawable.recipe2, R.drawable.buttob60));
        listRecipePages.add(new RecipePages("Ice Grape", "Lorem Ipsum dolor amet gihan", "Ice", "22.7kCal", "27 min", R.drawable.recipe3, R.drawable.buttob60));
        listRecipePages.add(new RecipePages("Fruit Soup", "Lorem Ipsum dolor amet gihan", "Soup", "35.5kCal", "5 min", R.drawable.recipe4, R.drawable.buttob60));
        listRecipePages.add(new RecipePages("Orange Juice", "Lorem Ipsum dolor amet gihan", "Juice", "12 kCal", "3 min", R.drawable.recipe5, R.drawable.buttob60));
        listRecipePages.add(new RecipePages("Salad Buah", "Lorem Ipsum dolor amet gihan", "Salad", "33.3kCal", "25 min", R.drawable.recipe1, R.drawable.buttob60));
        listRecipePages.add(new RecipePages("Ice Grape", "Lorem Ipsum dolor amet gihan", "Ice", "22.7kCal", "27 min", R.drawable.recipe3, R.drawable.buttob60));
        listRecipePages.add(new RecipePages("Orange Juice", "Lorem Ipsum dolor amet gihan", "Juice", "12 kCal", "3 min", R.drawable.recipe5, R.drawable.buttob60));
        listRecipePages.add(new RecipePages("Fruit Soup", "Lorem Ipsum dolor amet gihan", "Soup", "35.5kCal", "5 min", R.drawable.recipe4, R.drawable.buttob60));
        listRecipePages.add(new RecipePages("Mango Pudding", "Lorem Ipsum dolor amet gihan", "Pudding", "12.4kCal", "20 min", R.drawable.recipe2, R.drawable.buttob60));
    }

}