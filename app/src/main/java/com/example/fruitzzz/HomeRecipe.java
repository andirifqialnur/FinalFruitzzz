package com.example.fruitzzz;

public class HomeRecipe {

    private String Title, SubTitle, Calories, Time, Amount;
    private Integer Image;

    // Constructor
    public HomeRecipe(String title, String subTitle, String calories, String time, String amount, Integer image) {
        Title = title;
        SubTitle = subTitle;
        Calories = calories;
        Time = time;
        Amount = amount;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public Integer getImage() {
        return Image;
    }

    public void setImage(Integer image) {
        Image = image;
    }
}
