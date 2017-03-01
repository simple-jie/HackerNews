package com.simple_jie.data.entity;

import com.simple_jie.data.dao.CategoryConverter;
import com.simple_jie.data.dao.IntegerListConverter;
import com.simple_jie.domain.entities.Category;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

import static com.simple_jie.data.entity.CategoryNewsEntityDao.Properties.Id;

/**
 * Created by Xingbo.Jie on 2/3/17.
 */

@Entity
public class CategoryNewsEntity {
    @Id
    Long id;

    @Unique
    @Convert(columnType = String.class, converter = CategoryConverter.class)
    Category category;


    @Convert(converter = IntegerListConverter.class, columnType = String.class)
    List<Integer> ids;

    long refeshTime;


    @Generated(hash = 15996917)
    public CategoryNewsEntity(Long id, Category category, List<Integer> ids,
            long refeshTime) {
        this.id = id;
        this.category = category;
        this.ids = ids;
        this.refeshTime = refeshTime;
    }


    @Generated(hash = 450967365)
    public CategoryNewsEntity() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Category getCategory() {
        return this.category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }


    public List<Integer> getIds() {
        return this.ids;
    }


    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }


    public long getRefeshTime() {
        return this.refeshTime;
    }


    public void setRefeshTime(long refeshTime) {
        this.refeshTime = refeshTime;
    }
}
