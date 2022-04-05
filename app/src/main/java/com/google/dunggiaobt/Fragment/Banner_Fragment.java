package com.google.dunggiaobt.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.google.dunggiaobt.Adapter.BannerAdapter;
import com.google.dunggiaobt.Model.Quangcao;
import com.google.dunggiaobt.R;
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Banner_Fragment extends Fragment {
    View view;
    ViewPager2 viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_banner,container, false);
        viewPager= view.findViewById(R.id.view_pager_2);
        anhxa();
        GetData();
        return view;
    }
    private void  anhxa(){
    //   circleIndicator=view.findViewById(R.id.indicatordefault);
   }
    private void  GetData(){
        Dataservice dataservice= APIService.getService();
        Call<List<Quangcao>> callback =dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(@NonNull Call<List<Quangcao>> call, @NonNull Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banner=(ArrayList<Quangcao>)response.body();
              Log.d("dung11", Objects.requireNonNull(banner).get(0).getTenBaiHat());

               bannerAdapter= new BannerAdapter(getActivity()  ,banner);
                viewPager.setAdapter(bannerAdapter);
               // circleIndicator = (CircleIndicator) view.findViewById(R.id.indicatordefault);
              //  circleIndicator.setViewPager(viewPager);

                handler=new Handler();

               runnable = () -> {
                   currentItem=viewPager.getCurrentItem();
                   currentItem++;
                   if(currentItem >= Objects.requireNonNull(viewPager.getAdapter()).getItemCount()){
                       currentItem=0;
                   }
                   viewPager.setCurrentItem(currentItem,true);
                  handler.postDelayed(runnable,4500);
               };
               handler.postDelayed(runnable,4500);
            }

            @Override
            public void onFailure(@NonNull Call<List<Quangcao>> call, @NonNull Throwable t) {

            }
        });
    }
}
