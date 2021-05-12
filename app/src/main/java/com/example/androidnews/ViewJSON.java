package com.example.androidnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnews.models.Article;

public class ViewJSON extends AppCompatActivity {

    TextView title, author, content, date;
    ImageView photo;
    Button link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_json);
        Article article=(Article) getIntent().getSerializableExtra("article");
        title = (TextView) findViewById(R.id.tvTitle);
        author = (TextView) findViewById(R.id.tvAuthor);
        content = (TextView) findViewById(R.id.tvContent);
        date = (TextView) findViewById(R.id.tvDate);
        photo = (ImageView) findViewById(R.id.ivPhoto);
        link = (Button) findViewById(R.id.btLink);
        Glide.with(this).load(article.getUrlToImage()).into(photo);
        title.setText(article.getTitle());
        author.setText(article.getAuthor());
        date.setText(article.getPublishedAt());
        content.setText(article.getContent());

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(article.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

}