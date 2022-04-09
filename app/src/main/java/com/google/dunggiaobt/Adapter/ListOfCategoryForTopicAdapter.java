package com.google.dunggiaobt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.dunggiaobt.Acrivity.DanhsachbaihatActivity;
import com.google.dunggiaobt.Model.Category;
import com.google.dunggiaobt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListOfCategoryForTopicAdapter extends RecyclerView.Adapter<ListOfCategoryForTopicAdapter.ViewHolder>{

    Context context;
    ArrayList<Category> categoryArrayList;

    public ListOfCategoryForTopicAdapter(Context context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lines_categories_for_topic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryArrayList.get(position);
        Picasso.with(context).load(category.getHinhTheLoai()).into(holder.imageView);
        holder.txtCategoryName.setText(category.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtCategoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewCategoriesForTopic);
            txtCategoryName = itemView.findViewById(R.id.textViewCategoriesForTopic);
            itemView.setOnClickListener(view ->{
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("idCategory", categoryArrayList.get(getPosition()));
                context.startActivity(intent);
            } );
        }
    }
}
