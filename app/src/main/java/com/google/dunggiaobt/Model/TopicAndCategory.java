//Nguyen Van Cong - 191202433
package com.google.dunggiaobt.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TopicAndCategory {

    @SerializedName("Categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("Topics")
    @Expose
    private List<Topic> topics = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

}
