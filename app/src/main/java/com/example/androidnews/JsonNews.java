package com.example.androidnews;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidnews.models.Article;
import com.example.androidnews.models.request.EverythingRequest;
import com.example.androidnews.models.response.ArticleResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.intellij.lang.annotations.Language;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;

public class JsonNews extends AppCompatActivity {
    String lan = "es";
//    String urlJSON = "https://newsapi.org/v2/everything?q=Android&sortBy=popularity&language="+language+"&apiKey=7a427c6741584333a289dc1d981ee019";
    String key="7a427c6741584333a289dc1d981ee019";
    ProgressDialog progressDialog;
    public List<Article> newsList;
    JSONAdapter adapter;
    RecyclerView recyclerView;
    Spinner language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_news);
        newsList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerJSON);
        language = (Spinner) findViewById(R.id.spLanguage);
        adapter = new JSONAdapter( JsonNews.this);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.spOptions, android.R.layout.simple_spinner_item);
        language.setAdapter(arrayAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(JsonNews.this));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                Article article = newsList.get(position);
                Intent intent = new Intent(JsonNews.this, ViewJSON.class);
                intent.putExtra("article", article);
                startActivity(intent);
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
            }
        }));

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position", String.valueOf(position));

                if (position == 0) {
                    lan = "es";
                }
                if (position == 1) {
                    lan = "en";
                }
                getjson(key, lan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    public void getjson(String key,String Language){
        Log.d("pok", key);
        NewsApiClient newsApiClient = new NewsApiClient(key);
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q("Android")
                        .language(Language)
                        .sortBy("popularity")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        Log.d("pok",String.valueOf(response.getTotalResults()));
                        Log.d("pok",response.getArticles().get(0).getTitle());
                        Log.d("pok",response.getArticles().get(0).getContent());
                        newsList=response.getArticles().subList(0,9);
                        adapter.setList(newsList);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );

    }
    public void refresh(){
        adapter.notifyDataSetChanged();
    }

    /*public class MyAsyncTasks extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            progressDialog = new ProgressDialog(JsonNews.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            getjson(key,lan);
            //adapter=new JSONAdapter(newsArrayList,JsonNews.this);
            //recyclerView.setAdapter(adapter);
            return "fin";
        }
        @Override
        protected void onPostExecute(String s) {
            adapter=new JSONAdapter(newsArrayList,JsonNews.this);
            refresh();
            recyclerView.setAdapter(adapter);
            refresh();

            progressDialog.dismiss();
        }
    }*/
}