package com.example.ahmed.bbc_sport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
TextView detialsText;
TextView pubText;
ImageView imageView;
TextView urltext;
CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        detialsText = (TextView)findViewById(R.id.textDetials);
        pubText = (TextView) findViewById(R.id.textpublishedAt);
        imageView = (ImageView) findViewById(R.id.imageDetails);
        urltext = (TextView) findViewById(R.id.textLink);
        final Intent intent = getIntent();

        urltext.setText(intent.getStringExtra("url"));
        pubText.setText("Published At : "+intent.getStringExtra("publishedAt"));
        detialsText.setText(intent.getStringExtra("details"));
        Picasso.with(getApplicationContext()).load(intent.getStringExtra("poster")).error(R.drawable.ic_launcher_background).into(imageView);
        String title= intent.getStringExtra("title");
        setTitle(title);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra("url")));
                startActivity(browserIntent);
            }
        });
    }
}
