package com.example.androidnews.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidnews.models.ArticleDB;

import java.util.List;

@Dao
public interface DAOArticle {
    @Insert
    public void insert(ArticleDB article);
    @Update
    public void update(ArticleDB article);
    @Delete
    public void delete(ArticleDB article);
    @Query("SELECT * FROM Article")
    public List<ArticleDB> getArticles();
    @Query("SELECT * FROM Article where id = :id")
    public ArticleDB getArticle(Long id);
}
