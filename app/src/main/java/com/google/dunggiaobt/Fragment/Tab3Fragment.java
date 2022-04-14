package com.google.dunggiaobt.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.google.dunggiaobt.R;

import java.util.ArrayList;
import java.util.Objects;

public class Tab3Fragment extends Fragment {
    View view;
    Toolbar toolbar;
    Button button;
    ListView listView;
    ArrayList<String> songArrayList;
    ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        toolbar = view.findViewById(R.id.toolbarCaNhan);
        toolbar.setNavigationOnClickListener(view -> getActivity().finish());
        button = view.findViewById(R.id.buttonRefesh);
        listView = view.findViewById(R.id.listViewCaNhan);
        button.setOnClickListener(view1 -> accessMediaStore());

        return view;
    }

    private void accessMediaStore(){
        String [] projection = {
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED,
                MediaStore.MediaColumns.MIME_TYPE
        };

        CursorLoader cursorLoader = new CursorLoader(getActivity(), MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor!= null){
            cursor.moveToFirst();

            songArrayList = new ArrayList<>();

            while (!cursor.isAfterLast()){
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    if (cursor.getString(i).endsWith(".mp3")){
                        songArrayList.add(cursor.getString(i));
                    }

                }

                cursor.moveToNext();
            }
            adapter = new ArrayAdapter(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, songArrayList);
            listView.setAdapter(adapter);
            //cursorLoader.cancelLoad();
            cursor.close();
        }

    }
}