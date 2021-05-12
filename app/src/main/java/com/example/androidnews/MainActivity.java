package com.example.androidnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void controlU(View view) {
        Intent intent = new Intent(this, DBMainActivity.class);
        startActivity(intent);
    }

    public void controlN(View view) {
        Intent intent = new Intent(this, JsonNews.class);
        startActivity(intent);
    }
}