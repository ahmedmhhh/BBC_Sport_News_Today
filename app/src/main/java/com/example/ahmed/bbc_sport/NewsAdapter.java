package com.example.ahmed.bbc_sport;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{

    List <News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_news,parent,false);
        NewsHolder newsHolder = new NewsHolder(row);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        News news = newsList.get(position);
        holder.newsTitle.setText(news.title);
      //  holder.newsDesc.setText(news.description);
       // holder.newsDate.setText(news.publishedAt);
        Picasso.with(holder.poster.getContext()).load(news.urlToImage).error(R.drawable.ic_launcher_background).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder{
        TextView newsTitle,newsDate,newsDesc;
        ImageView poster;
        public NewsHolder(View itemView) {
            super(itemView);
            newsTitle=(TextView)itemView.findViewById(R.id.newsTitleTXT);
            newsDate = (TextView) itemView.findViewById(R.id.publishedAtTXT);
            newsDesc = (TextView) itemView.findViewById(R.id.newsDesc);
            poster = (ImageView) itemView.findViewById(R.id.newsposteIMG);

        }
    }
}
