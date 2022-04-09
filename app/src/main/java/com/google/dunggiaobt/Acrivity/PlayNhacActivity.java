package com.google.dunggiaobt.Acrivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.dunggiaobt.Adapter.ViewPagerPlayListNhacAdapter;
import com.google.dunggiaobt.Fragment.Fragment_Dia_Nhac;
import com.google.dunggiaobt.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.google.dunggiaobt.Model.BaiHat;
import com.google.dunggiaobt.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class PlayNhacActivity extends AppCompatActivity {

Toolbar toolbarplaynhac;
TextView txtTimeSong,txtTotaltimesong;
SeekBar sktime;
ImageButton imgplay,imgrepeat,imgnext,imgpre,imgrandom;
ViewPager viewPagerplaynhac;

public static ArrayList<BaiHat> mangbaihat=new ArrayList<>();
public static ViewPagerPlayListNhacAdapter nhacAdapter;
Fragment_Dia_Nhac fragmentDiaNhac;
Fragment_Play_Danh_Sach_Cac_Bai_Hat fragmentPlayDanhSachCacBaiHat;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getData();
        init();
        evenClick();

    }

    private void evenClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){

            @Override
            public void run() {
                nhacAdapter.getItem(1);
                if (mangbaihat.size() > 0){
                    fragmentDiaNhac.PlayNhac(mangbaihat.get(0).getHinhbaihat());
                    handler.removeCallbacks(this);
                }else{
                    handler.postDelayed(this, 300);
                }
            }
        }, 500);

        imgplay.setOnClickListener(view -> {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                imgplay.setImageResource(R.drawable.iconplay);
                Fragment_Dia_Nhac.animation.pause();

            }else {
                mediaPlayer.start();
                imgplay.setImageResource(R.drawable.iconpause);
                Fragment_Dia_Nhac.animation.resume();
            }
        });
    }

    private void getData() {
        Intent intent=getIntent();
        mangbaihat.clear();
       if(intent!=null){
           if(intent.hasExtra("cakhuc"))
           {
               BaiHat baiHat=intent.getParcelableExtra("cakhuc");
               mangbaihat.add(baiHat);

           }
           if(intent.hasExtra("cacbaihat"))
           {
               mangbaihat= intent.getParcelableArrayListExtra("cacbaihat");
           }

       }
    }

    private  void init(){
        toolbarplaynhac=findViewById(R.id.toolbarplaynhac);
        txtTimeSong=findViewById(R.id.textviewtimesong);
        txtTotaltimesong=findViewById(R.id.textviewtotaltimesong);
        sktime=findViewById(R.id.seekbarsong);
        imgplay=findViewById(R.id.imagebuttonplay);
        imgrepeat=findViewById(R.id.imagebuttonrepeat);
        imgnext=findViewById(R.id.imagebuttonnext);
        imgrandom=findViewById(R.id.imagebuttonsuffle);
        imgpre=findViewById(R.id.imagebuttonpre);
        viewPagerplaynhac=findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(view -> {
            finish();
            mediaPlayer.stop();
            mangbaihat.clear();
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);

        fragmentDiaNhac = new Fragment_Dia_Nhac();
        fragmentPlayDanhSachCacBaiHat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        nhacAdapter = new ViewPagerPlayListNhacAdapter(getSupportFragmentManager());
        nhacAdapter.addFragment(fragmentPlayDanhSachCacBaiHat);
        nhacAdapter.addFragment(fragmentDiaNhac);
        viewPagerplaynhac.setAdapter(nhacAdapter);

        fragmentDiaNhac = (Fragment_Dia_Nhac) nhacAdapter.getItem(1);
        if(mangbaihat.size()>0){
            Objects.requireNonNull(getSupportActionBar()).setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }
    class PlayMp3 extends AsyncTask<String ,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);

            try {
                mediaPlayer =new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            }catch (IOException e){
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();

        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());

    }
}