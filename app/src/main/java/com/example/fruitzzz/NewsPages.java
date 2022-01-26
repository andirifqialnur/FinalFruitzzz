package com.example.fruitzzz;

public class NewsPages {

    private String Author, Title, Description, Url, UrlToImage, PublishedAt;
    public NewsPages(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.Author = author;
        this.Title = title;
        this.Description = description;
        this.Url = url;
        this.UrlToImage = urlToImage;
        this.PublishedAt = publishedAt;
    }

    public String getAuthor() { return Author; }
    public void setAuthor(String author) { Author = author; }

    public String getTitle() { return Title; }
    public void setTitle(String title) { Title = title; }

    public String getDescription() { return Description; }
    public void setDescription(String description) { Description = description; }

    public String getUrl() { return Url; }
    public void setUrl(String url) { Url = url; }

    public String getUrlToImage() { return UrlToImage; }
    public void setUrlToImage(String urlToImage) { UrlToImage = urlToImage; }

    public String getPublishedAt() { return PublishedAt; }
    public void setPublishedAt(String publishedAt) { PublishedAt = publishedAt; }
}
