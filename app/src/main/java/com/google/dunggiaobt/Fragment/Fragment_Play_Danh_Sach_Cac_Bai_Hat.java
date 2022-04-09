//Nguyen Van Cong
package com.google.dunggiaobt.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Acrivity.PlayNhacActivity;
import com.google.dunggiaobt.Adapter.PlayNhacAdapter;
import com.google.dunggiaobt.R;

public class Fragment_Play_Danh_Sach_Cac_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerView;
    PlayNhacAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewPlayBaiHat);
        if (PlayNhacActivity.mangbaihat.size() > 0){
            adapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }
        
        return view;
    }
}
