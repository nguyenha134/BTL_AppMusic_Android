package com.google.dunggiaobt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Acrivity.ListOfCategoriesForTopicActivity;
import com.google.dunggiaobt.Model.Topic;
import com.google.dunggiaobt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListOfAllTopicsAdapter extends RecyclerView.Adapter<ListOfAllTopicsAdapter.ViewHolder>{

    Context context;
    ArrayList<Topic> topicArrayList;

    public ListOfAllTopicsAdapter(Context context, ArrayList<Topic> topicArrayList) {
        this.context = context;
        this.topicArrayList = topicArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lines_all_topics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic topic = topicArrayList.get(position);
        Picasso.with(context).load(topic.getHinhChuDe()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return topicArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewLinesAllTopics);
            imageView.setOnClickListener(view ->{
                Intent intent = new Intent(context, ListOfCategoriesForTopicActivity.class);
                intent.putExtra("topic", topicArrayList.get(getPosition()));
                context.startActivity(intent);
            } );
        }
    }
}
