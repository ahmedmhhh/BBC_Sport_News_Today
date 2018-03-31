package com.example.ahmed.bbc_sport;



public class News {
    String title,description,url,publishedAt;

    String urlToImage;
    public News(String title, String description, String  urlToImage, String publishedAt) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }


}
