package com.example.ahmed.bbc_sport;



public class News {
    String title,description,url,publishedAt;

    String urlToImage;

    public News(String title, String description, String url, String publishedAt, String urlToImage) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.publishedAt = publishedAt;
        this.urlToImage = urlToImage;
    }
}
