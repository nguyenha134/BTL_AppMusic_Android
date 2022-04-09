package com.google.dunggiaobt.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.dunggiaobt.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Dia_Nhac extends Fragment {

    View view;
    CircleImageView circleImageView;
    public static ObjectAnimator animation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac, container, false);
        circleImageView = view.findViewById(R.id.imageViewCircle);
        animation = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f);
        animation.setDuration(10000);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.setRepeatMode(ValueAnimator.RESTART);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
        return view;
    }

    public void PlayNhac(final String hinhAnh) {
        Handler handler=new Handler();
        handler.postDelayed(() -> Picasso.with(getContext()).load(hinhAnh).into(circleImageView),300);
    }
}
