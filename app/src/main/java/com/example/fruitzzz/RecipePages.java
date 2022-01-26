package com.example.fruitzzz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipePages {
    private String Title, SubTitle, Category, Calories, Time;
    private Integer Image, BtnIntent;

    // Constructor
    public RecipePages(String title, String subTitle, String category, String calories, String time, Integer image, Integer btnIntent) {
        Title = title;
        SubTitle = subTitle;
        Category = category;
        Calories = calories;
        Time = time;
        Image = image;
        BtnIntent = btnIntent;
    }

    // Getter
    public String getTitle() { return Title; }

    public String getSubTitle() { return SubTitle; }

    public String getCategory() { return Category; }

    public String getCalories() { return Calories; }

    public String getTime() { return Time; }

    public Integer getImage() { return Image; }

    public Integer getBtnIntent() { return BtnIntent; }

    //Setter

    public void setTitle(String title) { Title = title; }

    public void setSubTitle(String subTitle) { SubTitle = subTitle; }

    public void setCategory(String category) { Category = category; }

    public void setCalories(String calories) { Calories = calories; }

    public void setTime(String time) { Time = time; }

    public void setImage(Integer image) { Image = image; }

    public void setBtnIntent(Integer btnIntent) { BtnIntent = btnIntent; }
}
