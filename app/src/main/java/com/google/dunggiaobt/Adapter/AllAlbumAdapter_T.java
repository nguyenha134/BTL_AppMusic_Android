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


public class AllAlbumAdapter_T extends  RecyclerView.Adapter<AllAlbumAdapter_T.ViewHolder>{

    Context context;
    ArrayList<Album> mangalbum;

    public AllAlbumAdapter_T(Context context, ArrayList<Album> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }
    int ii=0;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_album, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        //
        Picasso.with(context).load(mangalbum.get(ii).getHinhAlbum()).into(txtallalbum);
        txttenallalbum.setText(mangalbum.get(ii).getTenAlbum());
        txttencasiallalbum.setText(mangalbum.get(ii).getTencasiAlbum());
        ii++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }
    ImageView txtallalbum;
    TextView txttenallalbum,txttencasiallalbum;
    public  class  ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtallalbum=itemView.findViewById(R.id.imageviewdanhsachcacalbum);
            txttenallalbum=itemView.findViewById(R.id.textviewtendanhsachcacalbum);
            txttencasiallalbum=itemView.findViewById(R.id.textviewtencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album",mangalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
