package com.google.dunggiaobt.Acrivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Model.Topic;
import com.google.dunggiaobt.R;

public class ListOfCategoriesForTopicActivity extends AppCompatActivity {

    Topic topic;
    Toolbar toolbar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_categories_for_topic);
        GetIntent();
        init();
    }

    private void init(){
        toolbar = findViewById(R.id.toolbarCategoriesForTopic);
        recyclerView = findViewById(R.id.recyclerViewCategoriesForTopic);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(topic.getTenChuDe());
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }
    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("topic")){
            topic = (Topic) intent.getSerializableExtra("topic");
        }
    }


}