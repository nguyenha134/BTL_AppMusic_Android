package com.google.dunggiaobt.Acrivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.dunggiaobt.Adapter.DanhsachbaihatAdapter;
import com.google.dunggiaobt.Model.Album;
import com.google.dunggiaobt.Model.BaiHat;
import com.google.dunggiaobt.Model.Playlist;
import com.google.dunggiaobt.Model.Quangcao;
import com.google.dunggiaobt.R;
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imgDanhsachcakhuc;
    Quangcao quangcao;
    Album album;
    ArrayList<BaiHat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    Playlist playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        DataIntent();
        anhxa();
        init();

    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaitheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private  void init(){
        //Ha
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        //end ha
        if(quangcao !=null && !quangcao.getTenBaiHat().equals("")){
            SetValueInView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
            GetDataQuangCao(quangcao.getIdQuangCao());
         }
        //truong
        if(album !=null && !album.getTenAlbum().equals("")){
            SetValueInView(album.getTenAlbum(),album.getHinhAlbum());
           GetDaTaAlbum(album.getIdAlbum());
        }
        //end truong

        if(playlist!=null && !playlist.getTen().equals("")){
            SetValueInView(playlist.getTen(), playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }

    }
    //truong
    private void GetDaTaAlbum(String idAlbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihattheoalbum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat=(ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat=(ArrayList<BaiHat>) response.body();
                Toast.makeText(DanhsachbaihatActivity.this, mangbaihat.size()+" 2", Toast.LENGTH_SHORT).show();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));

                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void SetValueInView(String ten,String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try{
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgDanhsachcakhuc);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout =findViewById(R.id.collapsingtollbar);
        toolbar =findViewById(R.id.toolbardanhsachbaihat);
        recyclerViewdanhsachbaihat=findViewById(R.id.recyclerviewdanhsachbaihat);
        floatingActionButton =findViewById(R.id.floatingactionbutton);
        imgDanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent =getIntent();
        if(intent!= null){
            if(intent.hasExtra("banner")){
                quangcao =(Quangcao) intent.getSerializableExtra("banner");
                }
            //ha
            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            //end ha
            //truong
            if(intent.hasExtra("album"))
            {
                album = (Album) intent.getSerializableExtra("album");
            }
            //end truong
        }
    }
}
