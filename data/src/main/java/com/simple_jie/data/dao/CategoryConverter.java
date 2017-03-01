package com.simple_jie.data.dao;

import com.simple_jie.domain.entities.Category;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Xingbo.Jie on 2/3/17.
 */

public class CategoryConverter implements PropertyConverter<Category, String> {
    @Override
    public Category convertToEntityProperty(String databaseValue) {
        return Category.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(Category entityProperty) {
        return entityProperty.name();
    }
}
