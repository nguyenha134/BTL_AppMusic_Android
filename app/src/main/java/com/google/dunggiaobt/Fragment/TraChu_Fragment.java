package com.google.dunggiaobt.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.dunggiaobt.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TraChu_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TraChu_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TraChu_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("phamvantruong", "Fragment 1 " );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("phamvantruong", "Fragment 1 " );
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TraChu_Fragment newInstance(String param1, String param2) {
        TraChu_Fragment fragment = new TraChu_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    View view;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trangchu,container, false);
        return view;

    }
    public  void  reLoadData()
    {
        Toast.makeText(getActivity(), "reload Fragment 1", Toast.LENGTH_SHORT).show();
    }
}