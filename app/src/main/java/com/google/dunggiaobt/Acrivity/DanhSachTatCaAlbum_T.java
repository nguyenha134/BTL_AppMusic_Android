package com.google.dunggiaobt.Acrivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Adapter.AllAlbumAdapter_T;
import com.google.dunggiaobt.Model.Album;
import com.google.dunggiaobt.R;
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaAlbum_T extends AppCompatActivity {
    //    //tu album hot staractivity sang day
    RecyclerView recyclerViewallalbum;
    Toolbar toolbarallalbum;
    AllAlbumAdapter_T allAlbumAdapter_t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_album_t);
        recyclerViewallalbum=findViewById(R.id.recyclerviewallalbum);
        toolbarallalbum=findViewById(R.id.toolbarallalbum);
//
        setSupportActionBar(toolbarallalbum);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarallalbum.setNavigationOnClickListener(view -> finish());

        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Album>> callback =dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(@NonNull Call<List<Album>> call, @NonNull Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>)response.body();
                allAlbumAdapter_t =new AllAlbumAdapter_T(DanhSachTatCaAlbum_T.this,albumArrayList);
                recyclerViewallalbum.setLayoutManager(new GridLayoutManager(DanhSachTatCaAlbum_T.this,2));
                recyclerViewallalbum.setAdapter(allAlbumAdapter_t);
            }

            @Override
            public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable t) {

            }
        });

    }
}