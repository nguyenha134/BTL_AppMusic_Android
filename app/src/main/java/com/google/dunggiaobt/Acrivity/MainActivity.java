package com.google.dunggiaobt.Acrivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.dunggiaobt.Adapter.MainViewPagerAdapter;
import com.google.dunggiaobt.Fragment.TraChu_Fragment;
import com.google.dunggiaobt.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
private ViewPager mViewPager;
private  BottomNavigationView mBottomNavigationView;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager=findViewById(R.id.view_parent);
        mBottomNavigationView=findViewById(R.id.bottom_navigation);

        MainViewPagerAdapter adapter=new MainViewPagerAdapter(getSupportFragmentManager (), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(adapter);
        //loan 2 pager
        mViewPager.setOffscreenPageLimit(2);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_tab_1).setChecked(true);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_tab_2).setChecked(true);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.menu_tab_3).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBottomNavigationView.setOnNavigationItemReselectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_tab_1:
                    mViewPager.setCurrentItem(0);
                    TraChu_Fragment tab1Fragment= (TraChu_Fragment) Objects.requireNonNull(mViewPager.getAdapter()).instantiateItem(mViewPager,0);
                    tab1Fragment.reLoadData();
                    break;
                case R.id.menu_tab_2:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.menu_tab_3:
                    mViewPager.setCurrentItem(2);
                    break;
            }
        });
    }
}