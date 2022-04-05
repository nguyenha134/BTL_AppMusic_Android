package com.google.dunggiaobt.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Acrivity.DanhSachTatCaAlbum_T;
import com.google.dunggiaobt.Adapter.AlbumAdapter_T;
import com.google.dunggiaobt.Model.Album;
import com.google.dunggiaobt.R;
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album_Hot_T extends Fragment {
    View view ;
    RecyclerView recyclerViewalbum;
    TextView txtxemthemalbum;
    TextView txtalbumhot;
    AlbumAdapter_T albumAdapterT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album_hot,container, false);
        recyclerViewalbum=view.findViewById(R.id.recyclerviewabum);
        txtxemthemalbum=view.findViewById(R.id.textviewxemthem);
        txtalbumhot=view.findViewById(R.id.textviewtitleAlbumm);

        txtxemthemalbum.setOnClickListener(view -> {
            Intent intent=new Intent(getActivity(), DanhSachTatCaAlbum_T.class);
            startActivity(intent);
        });
        GetData();
        return view;


    }
    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Album>> callback =dataservice.GetAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(@NonNull Call<List<Album>> call, @NonNull Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>)response.body();
                // Log.d("dung11",albumArrayList.get(2).getTenAlbum());

                albumAdapterT =new AlbumAdapter_T(getActivity(),albumArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewalbum.setLayoutManager(linearLayoutManager);
                recyclerViewalbum.setAdapter(albumAdapterT);
            }
            @Override
            public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable t) {
            }
        });
    }
}
