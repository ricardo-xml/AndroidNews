package com.example.androidnews.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.androidnews.models.ArticleDB;

@Database(entities = {ArticleDB.class},version = 3, exportSchema = false)
@TypeConverters({Convertidor.class})
public abstract class DBController extends RoomDatabase {
    public abstract DAOArticle getTablaArticle();
}