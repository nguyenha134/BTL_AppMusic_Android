package com.google.dunggiaobt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Acrivity.DanhsachbaihatActivity;
import com.google.dunggiaobt.Model.Album;
import com.google.dunggiaobt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter_T extends  RecyclerView.Adapter{
    Context context;
    ArrayList<Album> mangalbum;
    int ii=0;
    public AlbumAdapter_T(Context context, ArrayList<Album> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Picasso.with(context).load(mangalbum.get(ii).getHinhAlbum()).into(imageViewalbum);
        txttenalbum.setText(mangalbum.get(ii).getTenAlbum());
        txttencasialbum.setText(mangalbum.get(ii).getTencasiAlbum());
        ii++;
       return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }
    @Override
    public int getItemCount() {
        return mangalbum.size();
    }
    ImageView imageViewalbum;
    TextView txttenalbum,txttencasialbum;
    public  class  ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewalbum=itemView.findViewById(R.id.imageviewalbum);
            txttenalbum=itemView.findViewById(R.id.txttenalbum);
            txttencasialbum=itemView.findViewById(R.id.txtviewtencasialbum);
            itemView.setOnClickListener(view -> {
                Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("album",mangalbum.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
