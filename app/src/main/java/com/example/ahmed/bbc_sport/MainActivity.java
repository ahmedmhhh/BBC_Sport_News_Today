package com.example.ahmed.bbc_sport;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String url = "https://newsapi.org/v2/everything?sources=bbc-sport&apiKey=8aee3a81506b4b9fa8e9fae733ff5580";
    List<News> news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


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
                        String description=currentObject.getString("description");
                        String publishedAt=currentObject.getString("publishedAt");
                        String urlToImage=currentObject.getString("urlToImage");

                        News newsObject=new News(title,description,urlToImage,publishedAt);
                        news.add(newsObject);
                    }

                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }
                NewsAdapter newsAdapter = new NewsAdapter(news);
                recyclerView.setAdapter(newsAdapter);

            }
        }, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error On Your Network",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(newsRequest);

    }
}
