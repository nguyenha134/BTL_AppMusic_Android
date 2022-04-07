package com.google.dunggiaobt.Acrivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.dunggiaobt.Model.BaiHat;
import com.google.dunggiaobt.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {

Toolbar toolbarplaynhac;
TextView txtTimeSong,txtTotaltimesong;
SeekBar sktime;
ImageButton imgplay,imgrepeat,imgnext,imgpre,imgrandom;
ViewPager viewPagerplaynhac;

public static ArrayList<BaiHat> mangbaihat=new ArrayList<>();
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        getData();
        init();
        evenClick();

    }

    private void evenClick() {
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);

                }else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);

                }
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
               ArrayList<BaiHat> baihatList=intent.getParcelableArrayListExtra("cacbaihat");
               mangbaihat=baihatList;
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
      //  setSupportActionBar(toolbarplaynhac);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        if(mangbaihat.size()>0){
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
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            }catch (Exception e){

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