package com.google.dunggiaobt.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Adapter.BaihathotAdapter;
import com.google.dunggiaobt.Model.BaiHat;
import com.google.dunggiaobt.R;
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//VU
public class Fragment_Bai_Hat_Hot  extends Fragment {
    View view;
    RecyclerView recyclerViewBaiHatHot;
    BaihathotAdapter baihathotAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view =inflater.inflate(R.layout.fragment_bai_hat_hot,container,false);
        recyclerViewBaiHatHot = view.findViewById(R.id.recyclerviewBaihathot);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback =dataservice.GetBaiHatHot();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(@NonNull Call<List<BaiHat>> call, @NonNull Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList=(ArrayList<BaiHat>)response.body();
                baihathotAdapter= new BaihathotAdapter(getActivity(),baiHatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
              //  LinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaiHatHot.setLayoutManager(linearLayoutManager);
                recyclerViewBaiHatHot.setAdapter(baihathotAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<BaiHat>> call, @NonNull Throwable t) {

            }
        });
    }
}
