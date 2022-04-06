package com.google.dunggiaobt.Acrivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.dunggiaobt.Adapter.ListOfAllTopicsAdapter;
import com.google.dunggiaobt.Model.Topic;
import com.google.dunggiaobt.R;
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfAllTopicsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    ListOfAllTopicsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_all_topics);
        init();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Topic>> callback = dataservice.GetAllTopics();
        callback.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(@NonNull Call<List<Topic>> call, @NonNull Response<List<Topic>> response) {
                ArrayList<Topic> topicArrayList = (ArrayList<Topic>) response.body();
                adapter = new ListOfAllTopicsAdapter(ListOfAllTopicsActivity.this, topicArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(ListOfAllTopicsActivity.this, 1));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Topic>> call, @NonNull Throwable t) {

            }
        });
    }
    private void init(){
        recyclerView = findViewById(R.id.recyclerViewAllTopics);
        toolbar = findViewById(R.id.toolbarAllTopics);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }
}