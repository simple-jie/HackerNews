package com.simple_jie.domain.entities;

import java.util.List;

/**
 * Created by Xingbo.Jie on 2/3/17.
 */

public class CategoryNews {

    private Category category;
    private long refreshTime;
    private List<NewsItem> items;

    private CategoryNews(Builder builder) {
        setCategory(builder.category);
        setRefreshTime(builder.refreshTime);
        setItems(builder.items);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(CategoryNews copy) {
        Builder builder = new Builder();
        builder.category = copy.category;
        builder.refreshTime = copy.refreshTime;
        builder.items = copy.items;
        return builder;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public List<NewsItem> getItems() {
        return items;
    }

    public void setItems(List<NewsItem> items) {
        this.items = items;
    }

    public static final class Builder {
        private Category category;
        private long refreshTime;
        private List<NewsItem> items;

        private Builder() {
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder refreshTime(long refreshTime) {
            this.refreshTime = refreshTime;
            return this;
        }

        public Builder items(List<NewsItem> items) {
            this.items = items;
            return this;
        }

        public CategoryNews build() {
            return new CategoryNews(this);
        }
    }
}
