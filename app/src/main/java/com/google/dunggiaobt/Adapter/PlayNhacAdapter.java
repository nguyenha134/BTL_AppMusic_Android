package com.google.dunggiaobt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Model.BaiHat;
import com.google.dunggiaobt.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> mangBaiHat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangBaiHat.get(position);
        holder.txtCasi.setText(baiHat.getCasi());
        holder.txtIndex.setText(position + 1 + "");
        holder.txtTenBaiHat.setText(baiHat.getTenbaihat());

    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIndex, txtTenBaiHat, txtCasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.textViewPlayNhacIndex);
            txtCasi = itemView.findViewById(R.id.textViewPlayNhacTenCaSi);
            txtTenBaiHat = itemView.findViewById(R.id.textViewPlayNhacTenBaiHat);
        }
    }
}
