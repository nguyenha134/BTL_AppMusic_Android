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
import com.google.dunggiaobt.Model.Quangcao;
import com.google.dunggiaobt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter{
    Context context;
    ArrayList<Quangcao> arrayListBanner;
    int Position=0;
    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }
//    @Override
//    public int getCount() {
//        return arrayListBanner.size();
//    }

//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view==object;
//    }

//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        LayoutInflater inflater=LayoutInflater.from(context);
//        View view=inflater.inflate(R.layout.dong_banner,null);
//        position=0;
//        ImageView imgviewbackgroundbanner=view.findViewById(R.id.imgviewbackgroundbanner);
//        ImageView imgviewsongbanner=view.findViewById(R.id.imgviewbanner);
//        TextView txttitlesongbanner=view.findViewById(R.id.textviewtitlebannerbaihat);
//        TextView txtNoidung=view.findViewById(R.id.textviewnoidung);
//
//        Picasso.with(context).load(arrayListBanner.get(position).getHinhanh()).into(imgviewbackgroundbanner);
//        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(imgviewsongbanner);
//        txttitlesongbanner.setText(arrayListBanner.get(position).getTenBaiHat());
//        txtNoidung.setText(arrayListBanner.get(position).getNoidung());
//
//        container.addView(view);
//        return view;
//    }

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
       position=Position;
        LayoutInflater inflater = LayoutInflater.from(context);
        View heroView = inflater.inflate(R.layout.dong_banner, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhanh()).into(imgviewbackgroundbanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(imgviewsongbanner);
        txttitlesongbanner.setText(arrayListBanner.get(position).getTenBaiHat());
        txtNoidung.setText(arrayListBanner.get(position).getNoidung());
        Position++;
     //   parent.addView(heroView);

        int finalPosition = position;
        heroView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner",(Quangcao) arrayListBanner.get(finalPosition));
                context.startActivity(intent);
            }
        });
        return viewHolder;

    }
    ImageView imgviewbackgroundbanner;
    ImageView imgviewsongbanner;
    TextView txttitlesongbanner;
    TextView txtNoidung;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View view) {
            super(view);

            imgviewbackgroundbanner=view.findViewById(R.id.imgviewbackgroundbanner);
             imgviewsongbanner=view.findViewById(R.id.imgviewbanner);
             txttitlesongbanner=view.findViewById(R.id.textviewtitlebannerbaihat);
             txtNoidung=view.findViewById(R.id.textviewnoidung);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }
    @Override
    public int getItemCount() {
         return arrayListBanner.size();
    }
}
