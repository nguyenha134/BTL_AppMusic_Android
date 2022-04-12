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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangbaihat;
    static ArrayList<String> loved = new ArrayList<>();
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
            imgluotthich.setOnClickListener(view -> {
//                Intent intent=new Intent(context, PlayNhacActivity.class);
//                intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
//                context.startActivity(intent);
                boolean Isloved = false;
                for (String i: loved) {
                    if(mangbaihat.get(getPosition()).getIdbaihat().contains(i))
                    {
                        Isloved = true;
                        break;
                    }
                }
                if(Isloved)
                {
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.updateluotthich("-1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua != null) {
                                if(ketqua.equals("Success")){
                                    imgluotthich.setImageResource(R.drawable.iconlove);
                                    loved.remove(mangbaihat.get(getPosition()).getIdbaihat());
                                    Toast.makeText(context,"Đã bỏ Thích",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    ketqua =null;
                                    Toast.makeText(context,"Lỗi !!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                        }
                    });
                }
                else
                {
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.updateluotthich("1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua != null) {
                                if(ketqua.equals("Success")){
                                    imgluotthich.setImageResource(R.drawable.iconloved);
                                    loved.add(mangbaihat.get(getPosition()).getIdbaihat());
                                    Toast.makeText(context,"Đã Thích",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    ketqua =null;
                                    Toast.makeText(context,"Lỗi !!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                        }
                    });
                }

//                imgluotthich.setEnabled(false);
            });

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
