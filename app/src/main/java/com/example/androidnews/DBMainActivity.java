package com.example.androidnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidnews.db.DAOArticle;
import com.example.androidnews.db.DBController;
import com.example.androidnews.models.ArticleDB;

import java.util.List;

public class DBMainActivity extends AppCompatActivity {

    RecyclerView rv;
    List<ArticleDB> ArticlesList;
    DAOArticle dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmain);

        rv=findViewById(R.id.rvArticles);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad UpdateArticleActivity.java
                ArticleDB article = ArticlesList.get(position);
                Intent intent = new Intent(DBMainActivity.this, UpdateArticleActivity.class);
                intent.putExtra("idMas", article.getId());
                startActivity(intent);
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                final ArticleDB articleselect = ArticlesList.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(DBMainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.delete(articleselect);
                                onResume();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Desea eliminar su Articulo?")
                        .create();
                dialog.show();

            }
        }));
    }
    @Override
    protected void onResume() {
        super.onResume();
        DBController db = Room.databaseBuilder(this, DBController.class, "DBNews").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        dao = db.getTablaArticle();
        ArticlesList = dao.getArticles();
        Adapter adapter = new Adapter(ArticlesList, this);
        rv.setAdapter(adapter);
    }

    public void Addmas(View v) {
        Intent intent = new Intent(this, AddArticleActivity.class);
        startActivity(intent);
    }
}