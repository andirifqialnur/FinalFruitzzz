package com.example.fruitzzz;

public class HomeInfo {

    private String TitleInfo, Author;
    private Integer Image, ViewAll;

    // Constructor
    public HomeInfo(String title, String author, Integer image, Integer viewAll) {
        TitleInfo = title;
        Author = author;
        Image = image;
        ViewAll = viewAll;
    }

    // Getter
    public String getTitleInfo() { return TitleInfo; }
    public String getAuthor() { return Author; }
    public Integer getImage() { return Image; }
    public Integer getViewAll() { return ViewAll; }

    // Setter
    public void setTitleInfo(String title) { TitleInfo = title; }
    public void setAuthor(String author) { Author = author; }
    public void setImage(Integer image) { Image = image; }
    public void setViewAll(Integer viewAll) { ViewAll = viewAll; }
}
