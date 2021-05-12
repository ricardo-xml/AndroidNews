package com.example.androidnews.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Article",indices = {@Index("Id")})
public class ArticleDB implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "Id")
    private long id;
    @ColumnInfo(name = "Autor")
    private String autor;
    @ColumnInfo(name = "Titulo")
    private String titulo;
    @ColumnInfo(name = "Contenido")
    private String contenido;
    @ColumnInfo(name = "Fecha_Publicacion")
    private Date fechaPublicacion;
    @ColumnInfo(name = "Imagen")
    private byte[] imagen;

    public ArticleDB(long id, String autor, String titulo, String contenido,  Date fechaPublicacion, byte[] imagen){
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
        this.imagen = imagen;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}
