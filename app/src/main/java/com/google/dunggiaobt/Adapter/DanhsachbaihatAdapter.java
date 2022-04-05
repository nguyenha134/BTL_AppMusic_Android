package com.google.dunggiaobt.Adapter;

import static com.google.dunggiaobt.Adapter.BaihathotAdapter.baiHatArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Acrivity.PlayNhacActivity;
import com.google.dunggiaobt.Model.BaiHat;
import com.google.dunggiaobt.R;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangbaihat;
    public DanhsachbaihatAdapter(Context context,ArrayList<BaiHat> mangbaihat){
        this.context=context;
        this.mangbaihat=mangbaihat;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baihat =mangbaihat.get(position);

        holder.txtcasi.setText(baihat.getCasi());
       holder.txttenbaihat.setText(baihat.getTenbaihat());
       holder.txtindex.setText(position+1+"");

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat,txtcasi;
        ImageView imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewtencasi);
            txtindex =itemView.findViewById(R.id.textviewdanhsachindex);
            txttenbaihat =itemView.findViewById(R.id.textviewtenbaihat);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
