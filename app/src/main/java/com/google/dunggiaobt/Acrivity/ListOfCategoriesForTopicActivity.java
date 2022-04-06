package com.google.dunggiaobt.Acrivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Adapter.ListOfCategoryForTopicAdapter;
import com.google.dunggiaobt.Model.Category;
import com.google.dunggiaobt.Model.Topic;
import com.google.dunggiaobt.R;
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfCategoriesForTopicActivity extends AppCompatActivity {

    Topic topic;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ListOfCategoryForTopicAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_categories_for_topic);
        getIntent_();
        init();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Category>> callback = dataservice.GetCategoriesForTopic(topic.getIdChuDe());
        callback.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                ArrayList<Category> categoryArrayList = (ArrayList<Category>) response.body();
                adapter = new ListOfCategoryForTopicAdapter(ListOfCategoriesForTopicActivity.this, categoryArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(ListOfCategoriesForTopicActivity.this, 2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                Toast.makeText(ListOfCategoriesForTopicActivity.this, "Deo chay dc", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        toolbar = findViewById(R.id.toolbarCategoriesForTopic);
        recyclerView = findViewById(R.id.recyclerViewCategoriesForTopic);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(topic.getTenChuDe());
        toolbar.setNavigationOnClickListener(view -> finish());
    }
    private void getIntent_() {
        Intent intent = getIntent();
        if (intent.hasExtra("topic")){
            topic = (Topic) intent.getSerializableExtra("topic");
        }
    }


}