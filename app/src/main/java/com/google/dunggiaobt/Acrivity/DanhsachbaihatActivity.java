package com.google.dunggiaobt.Acrivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.dunggiaobt.Adapter.DanhsachbaihatAdapter;
import com.google.dunggiaobt.Model.Album;
import com.google.dunggiaobt.Model.BaiHat;
import com.google.dunggiaobt.Model.Category;
import com.google.dunggiaobt.Model.Playlist;
import com.google.dunggiaobt.Model.Quangcao;
import com.google.dunggiaobt.R;
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    Category category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
                evenclick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private  void init(){
        //Ha
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
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

        floatingActionButton.setEnabled(false);

        if(category!=null && !category.getTenTheLoai().equals("")){
            Toast.makeText(this, category.getIdTheLoai(), Toast.LENGTH_SHORT).show();
            SetValueInView(category.getTenTheLoai(), category.getHinhTheLoai());
            GetDataCategory(category.getIdTheLoai());
        }

    }

    private void GetDataCategory(String idCategory){
        Dataservice dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetListOfSongsByTheme(idCategory);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(@NonNull Call<List<BaiHat>> call, @NonNull Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenclick();
            }

            @Override
            public void onFailure(@NonNull Call<List<BaiHat>> call, @NonNull Throwable t) {
                Toast.makeText(DanhsachbaihatActivity.this, "No Data", Toast.LENGTH_SHORT).show();
            }
        });
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
                evenclick();
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
                Toast.makeText(DanhsachbaihatActivity.this, "No Data", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(DanhsachbaihatActivity.this, (mangbaihat != null ? mangbaihat.size() : 0) +" 2", Toast.LENGTH_SHORT).show();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenclick();

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
                Toast.makeText(DanhsachbaihatActivity.this, "GG", Toast.LENGTH_SHORT).show();
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
        } catch (IOException e){
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
            if(intent.hasExtra("idCategory")){
                category = (Category) intent.getSerializableExtra("idCategory");
            }
            //truong
            if(intent.hasExtra("album"))
            {
                album = (Album) intent.getSerializableExtra("album");
            }
            //end truong
        }
    }
    private  void  evenclick()
    {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent=new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
            intent.putExtra("cacbaihat",mangbaihat);
            startActivity(intent);
        });
    }


}
