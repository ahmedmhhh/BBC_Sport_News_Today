package com.example.ahmed.bbc_sport;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{
    private Context context;
    List <News> newsList;


    public NewsAdapter(List<News> newsList,Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_news,parent,false);
        NewsHolder newsHolder = new NewsHolder(row);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, final int position) {
        final News news = newsList.get(position);
        holder.newsTitle.setText(news.title);

        Picasso.with(holder.poster.getContext()).load(news.urlToImage).error(R.drawable.ic_launcher_background).into(holder.poster);
        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",news.title);
                intent.putExtra("url",news.url);
                intent.putExtra("publishedAt",news.publishedAt);
                intent.putExtra("details",news.description);
                intent.putExtra("poster",news.urlToImage);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    class NewsHolder extends RecyclerView.ViewHolder{
        TextView newsTitle;
        ImageView poster;
        public NewsHolder(View itemView) {
            super(itemView);
            newsTitle=(TextView)itemView.findViewById(R.id.newsTitleTXT);

            poster = (ImageView) itemView.findViewById(R.id.newsposteIMG);
        }
    }
}
