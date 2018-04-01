package com.example.ahmed.bbc_sport;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSON {
    final String url = "https://newsapi.org/v2/everything?sources=bbc-sport&apiKey=8aee3a81506b4b9fa8e9fae733ff5580";
    List<News> news;
    Context context;
    RecyclerView recyclerView;

    public JSON(List<News> news, Context context, RecyclerView recyclerView) {
        this.news = news;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public  void getData(){
        StringRequest newsRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("articles");
                    news = new ArrayList<>();
                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject currentObject=jsonArray.getJSONObject(i);
                        String title=currentObject.getString("title");
                        String url = currentObject.getString("url");
                        String description=currentObject.getString("description");
                        String publishedAt=currentObject.getString("publishedAt");
                        String urlToImage=currentObject.getString("urlToImage");

                        News newsObject=new News(title,description,url,publishedAt,urlToImage);
                        news.add(newsObject);
                    }

                }catch (Exception ex){
                    Toast.makeText(context,ex.getMessage(),Toast.LENGTH_LONG).show();
                }
                NewsAdapter newsAdapter = new NewsAdapter(news,context);
                recyclerView.setAdapter(newsAdapter);

            }
        }, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error On Your Network",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(context).add(newsRequest);
    }
}
