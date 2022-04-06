package com.google.dunggiaobt.Acrivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.dunggiaobt.Model.BaiHat;
import com.google.dunggiaobt.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txtTimeSong,txtTotaltimeSong;
    SeekBar sktime;
    ImageButton imgplay,imgrepeat,imgnext,imgpre,imgrandom;
    ViewPager viewPagerplaynhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        init();
        GetDataFromIntent();

    }

    private void GetDataFromIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("cakhuc"))
        {
            BaiHat baiHat=intent.getParcelableExtra("cakhuc");
            Toast.makeText(this, baiHat.getTenbaihat(), Toast.LENGTH_SHORT).show();
        }
        if(intent.hasExtra("cacbaihat"))
        {
            ArrayList<BaiHat> mangbaihat=intent.getParcelableArrayListExtra("cacbaihat");
            for( int i=0;i<mangbaihat.size();i++)
            {
                Log.d("truong",mangbaihat.get(i).getTenbaihat());
            }
        }
    }
//Vu
    private void init (){
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimeSong = findViewById(R.id.textviewtimesong);
        txtTotaltimeSong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgpre = findViewById(R.id.imagebuttonpre);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerplaynhac =findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
    }
    //endVU

}
