package com.example.fruitzzz;

import java.util.ArrayList;

public class NewsMain {

    private String status, totalResult;
    private ArrayList<NewsPages> articles;

    public NewsMain( String status, String totalResult, ArrayList<NewsPages> artices ) {
        this.articles = artices;
        this.status = status;
        this.totalResult = totalResult;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTotalResult() { return totalResult; }
    public void setTotalResult(String totalResult) { this.totalResult = totalResult; }

    public ArrayList<NewsPages> getArticles() { return articles; }
    public void setArticles(ArrayList<NewsPages> articles) { this.articles = articles; }

}
