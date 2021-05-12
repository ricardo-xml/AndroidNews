package com.example.androidnews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnews.models.ArticleDB;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ArticlesVH> {
    private List<ArticleDB> articles;
    private Context context;

    public Adapter(List<ArticleDB> articles, Context context){
        this.articles=articles;
        this.context=context;
    }

    @NonNull
    @NotNull
    @Override
    public Adapter.ArticlesVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View siteView = inflater.inflate(R.layout.row_db,parent,false);
        Adapter.ArticlesVH viewHolder = new Adapter.ArticlesVH(siteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter.ArticlesVH holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        ArticleDB article=articles.get(position);
        Bitmap bmp = BitmapFactory.decodeByteArray(article.getImagen(),0, article.getImagen().length);
        holder.titulo.setText(article.getTitulo());
        holder.autor.setText(article.getAutor());
        holder.fecha.setText(formatter.format(article.getFechaPublicacion()));
        holder.contenido.setText(String.valueOf(article.getContenido()));
        holder.imagen.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
    public static class ArticlesVH extends RecyclerView.ViewHolder {
        public TextView titulo,contenido,autor,fecha ;
        public ImageView imagen;
        public ArticlesVH(@NonNull @NotNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tvTitledb);
            autor=itemView.findViewById(R.id.tvAuthordb);
            contenido = itemView.findViewById(R.id.tvContentdb);
            fecha=itemView.findViewById(R.id.tvDatedb);
            imagen=itemView.findViewById(R.id.ivPhotodb);
        }
    }
}