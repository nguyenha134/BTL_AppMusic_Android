package com.google.dunggiaobt.Adapter;

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
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends  RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;
    ArrayList<String> loved = new ArrayList<>();

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txtTenBaiHat.setText(baiHat.getTenbaihat());
        holder.txtCaSi.setText(baiHat.getCasi());
        Picasso.with(context).load(baiHat.getLinkbaihat()).into(holder.imgBaiHat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBaiHat, txtCaSi;
        ImageView imgBaiHat, imgLuotThich;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.tvSearchTenBaiHat);
            txtCaSi = itemView.findViewById(R.id.tvSearchTenCaSi);
            imgBaiHat = itemView.findViewById(R.id.imgSearchBaiHat);
            imgLuotThich = itemView.findViewById(R.id.imgviewSearchLuotThich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                    context.startActivity(intent);

                }
            });
            imgLuotThich.setOnClickListener(view -> {
                //Intent intent=new Intent(context, PlayNhacActivity.class);
                //intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                //context.startActivity(intent);
                boolean Isloved = false;
//                Toast.makeText(context,loved.toString(),Toast.LENGTH_SHORT).show();
                for (String i : loved) {
                    if(i.contains(mangbaihat.get(getPosition()).getIdbaihat()))
                    {
                        Isloved = true;
                        break;
                    }
                }

                if(Isloved) {
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.updateluotthich("-1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                imgLuotThich.setImageResource(R.drawable.iconlove);
                                loved.remove(mangbaihat.get(getPosition()).getIdbaihat());
                                Toast.makeText(context,"Đã bỏ Thích",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context,"Lỗi !!",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
                else {
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.updateluotthich("1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                imgLuotThich.setImageResource(R.drawable.iconloved);
                                loved.add(mangbaihat.get(getPosition()).getIdbaihat());
                                Toast.makeText(context,"Đã Thích",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context,"Lỗi !!",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }

                //imgLuotthich.setEnabled(false);
            });
        }

    }
}
