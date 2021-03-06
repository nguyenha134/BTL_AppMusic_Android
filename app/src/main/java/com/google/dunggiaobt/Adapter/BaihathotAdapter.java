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

//vu
public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder>{
    static Context context;
    static ArrayList<BaiHat> baiHatArrayList;
    static ArrayList<String> loved = new ArrayList<>();

    public BaihathotAdapter(Context context,ArrayList<BaiHat> baiHatArrayList){
        this.context =context;
        this.baiHatArrayList =baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat=baiHatArrayList.get(position);
        holder.txtCasi.setText(baiHat.getCasi());
        holder.txtTen.setText(baiHat.getTenbaihat());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(holder.imgHinh);
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen,txtCasi;
        ImageView imgHinh,imgLuotthich;


        public ViewHolder(View itemView){
            super(itemView);
            txtTen =itemView.findViewById(R.id.texviewtenbaihathot);
            txtCasi =itemView.findViewById(R.id.texviewcasibaihathot);
            imgHinh = itemView.findViewById(R.id.imageviewBaihathot);
            imgLuotthich =itemView.findViewById(R.id.imageviewluotthich);
            imgLuotthich.setOnClickListener(view -> {
                //Intent intent=new Intent(context, PlayNhacActivity.class);
                //intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                //context.startActivity(intent);
                boolean Isloved = false;
//                Toast.makeText(context,loved.toString(),Toast.LENGTH_SHORT).show();
                for (String i : loved) {
                    if(i.contains(baiHatArrayList.get(getPosition()).getIdbaihat()))
                    {
                        Isloved = true;
                        break;
                    }
                }

                if(Isloved) {
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.updateluotthich("-1",baiHatArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                imgLuotthich.setImageResource(R.drawable.iconlove);
                                loved.remove(baiHatArrayList.get(getPosition()).getIdbaihat());
                                Toast.makeText(context,"???? b??? Th??ch",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context,"L???i !!",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
                else {
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.updateluotthich("1",baiHatArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")){
                                imgLuotthich.setImageResource(R.drawable.iconloved);
                                loved.add(baiHatArrayList.get(getPosition()).getIdbaihat());
                                Toast.makeText(context,"???? Th??ch",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(context,"L???i !!",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }

                //imgLuotthich.setEnabled(false);
            });

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
