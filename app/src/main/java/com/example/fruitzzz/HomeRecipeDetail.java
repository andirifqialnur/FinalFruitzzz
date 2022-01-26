package com.example.fruitzzz;

public class HomeRecipeDetail {
    String title, calorie, cook, amount;
    Integer desc, ingredient, step;
    int imageDetail;

    public HomeRecipeDetail(String title, String calorie, String cook, String amount, Integer desc, Integer ingredient, Integer step, int imageDetail) {
        this.title = title;
        this.calorie = calorie;
        this.cook = cook;
        this.amount = amount;
        this.desc = desc;
        this.ingredient = ingredient;
        this.step = step;
        this.imageDetail = imageDetail;
    }
}
