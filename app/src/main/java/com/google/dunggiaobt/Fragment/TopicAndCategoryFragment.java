//Nguyen Van Cong - 191202433
package com.google.dunggiaobt.Fragment;

import static android.widget.ImageView.ScaleType.FIT_XY;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.dunggiaobt.Model.Category;
import com.google.dunggiaobt.Model.Topic;
import com.google.dunggiaobt.Model.TopicAndCategory;
import com.google.dunggiaobt.R;
import com.google.dunggiaobt.Service.APIService;
import com.google.dunggiaobt.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicAndCategoryFragment extends Fragment {

    View view;
    HorizontalScrollView hScrollView;
    TextView txtMore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_topic_and_category, container, false);
        hScrollView = view.findViewById(R.id.horizontalScrollView);
        txtMore = view.findViewById(R.id.textViewMore);
        getData();
        return view;
    }

    private void getData(){
        Dataservice dataService = APIService.getService();
        Call<TopicAndCategory> callback = dataService.GetTopicAndCategory();
        callback.enqueue(new Callback<TopicAndCategory>() {
            @Override
            public void onResponse(@NonNull Call<TopicAndCategory> call, @NonNull Response<TopicAndCategory> response) {
                TopicAndCategory topicAndCategory = response.body();

                 ArrayList<Topic> topicArrayList = new ArrayList<>(Objects.requireNonNull(topicAndCategory).getTopics());
                ArrayList<Category> categoryArrayList = new ArrayList<>(Objects.requireNonNull(topicAndCategory).getCategories());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20, 10, 30);
                for (int i = 0; i < topicArrayList.size(); i++) {
                    CardView cardView = new CardView(requireActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(FIT_XY);
                    if (topicArrayList.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(topicArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }

                for (int i = 0; i < categoryArrayList.size(); i++) {
                    CardView cardView = new CardView(requireActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(FIT_XY);
                    if (categoryArrayList.get(i).getHinhTheLoai() != null){
                        Picasso.with(getActivity()).load(categoryArrayList.get(i).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }

                hScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(@NonNull Call<TopicAndCategory> call, @NonNull Throwable t) {

            }
        });
    }
}
