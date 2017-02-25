package com.simple_jie.domain.entities;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public enum Category {
    topstories("topstories"),
    newstories("newstories"),
    beststories("beststories");

    public String value;

    Category(String value) {
        this.value = value;
    }
}
