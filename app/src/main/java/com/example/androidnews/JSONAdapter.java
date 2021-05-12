package com.example.androidnews;

import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnews.models.Article;

import org.jetbrains.annotations.NotNull;

public class JSONAdapter
        extends RecyclerView.Adapter<JSONAdapter.JSONViewHolder>{

    List<Article> newsArrayList;
    Context context;

    public JSONAdapter(Context context){
        this.context = context;
    }

    public void setList(List<Article> list){
        newsArrayList=list;
    }
    @NonNull
    @NotNull
    @Override
    public JSONViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_json,parent,false);
        return new JSONViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull JSONAdapter.JSONViewHolder holder, int position) {
        Article article = newsArrayList.get(position);
        Glide.with(this.context).load(article.getUrlToImage()).into(holder.image);
        holder.title.setText(article.getTitle());
        holder.date.setText(article.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class JSONViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView date;
        private ImageView image;

        public JSONViewHolder(View row){
            super(row);
            this.image=(ImageView) itemView.findViewById(R.id.imageItemJ);
            this.title=(TextView) itemView.findViewById(R.id.titleItemJ);
            this.date=(TextView) itemView.findViewById(R.id.DateItemJ);
        }
    }
}
