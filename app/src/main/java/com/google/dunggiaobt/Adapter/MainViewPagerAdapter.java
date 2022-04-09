package com.google.dunggiaobt.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.dunggiaobt.Fragment.TraChu_Fragment;
import com.google.dunggiaobt.Fragment.Fragment_Tim_Kiem;
import com.google.dunggiaobt.Fragment.Tab3Fragment;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {


    public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new Fragment_Tim_Kiem();
            case 2:
                return new Tab3Fragment();
            default:
                return new TraChu_Fragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
