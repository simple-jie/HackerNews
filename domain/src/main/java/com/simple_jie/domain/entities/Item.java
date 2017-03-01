package com.simple_jie.domain.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public class Item implements Serializable {

    /**
     * by : dhouston
     * descendants : 71
     * id : 8863
     * kids : [8952,9224,8917,8884,8887,8943,8869,8958,9005,9671,8940,9067,8908,9055,8865,8881,8872,8873,8955,10403,8903,8928,9125,8998,8901,8902,8907,8894,8878,8870,8980,8934,8876]
     * score : 111
     * time : 1175714200
     * title : My YC app: Dropbox - Throw away your USB drive
     * type : story
     * url : http://www.getdropbox.com/u/2/screencast.html
     */

    private String by;
    private int descendants;
    private int id;
    private int score;
    private long time;
    private String title;
    private String type;
    private String url;
    private List<Integer> kids;
    /**
     * parent : 2921506
     * text : Aw shucks, guys ... you make me blush with your compliments.<p>Tell you what, Ill make a deal: I'll keep writing if you keep reading. K?
     */

    private int parent;
    private String text;
    private List<Integer> parts;

    private Item(Builder builder) {
        setBy(builder.by);
        setDescendants(builder.descendants);
        setId(builder.id);
        setScore(builder.score);
        setTime(builder.time);
        setTitle(builder.title);
        setType(builder.type);
        setUrl(builder.url);
        setKids(builder.kids);
        setParent(builder.parent);
        setText(builder.text);
        setParts(builder.parts);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Item copy) {
        Builder builder = new Builder();
        builder.by = copy.by;
        builder.descendants = copy.descendants;
        builder.id = copy.id;
        builder.score = copy.score;
        builder.time = copy.time;
        builder.title = copy.title;
        builder.type = copy.type;
        builder.url = copy.url;
        builder.kids = copy.kids;
        builder.parent = copy.parent;
        builder.text = copy.text;
        builder.parts = copy.parts;
        return builder;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Integer> getKids() {
        return kids;
    }

    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Integer> getParts() {
        return parts;
    }

    public void setParts(List<Integer> parts) {
        this.parts = parts;
    }

    public static final class Builder {
        private String by;
        private int descendants;
        private int id;
        private int score;
        private long time;
        private String title;
        private String type;
        private String url;
        private List<Integer> kids;
        private int parent;
        private String text;
        private List<Integer> parts;

        private Builder() {
        }

        public Builder by(String by) {
            this.by = by;
            return this;
        }

        public Builder descendants(int descendants) {
            this.descendants = descendants;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public Builder time(long time) {
            this.time = time;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder kids(List<Integer> kids) {
            this.kids = kids;
            return this;
        }

        public Builder parent(int parent) {
            this.parent = parent;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder parts(List<Integer> parts) {
            this.parts = parts;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
