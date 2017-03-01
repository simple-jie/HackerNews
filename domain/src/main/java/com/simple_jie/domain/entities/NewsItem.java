package com.simple_jie.domain.entities;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public class NewsItem {
    private Item item;
    private int id;

    public NewsItem(int id) {
        this.id = id;
    }

    public NewsItem(Item item) {
        this.id = item.getId();
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
