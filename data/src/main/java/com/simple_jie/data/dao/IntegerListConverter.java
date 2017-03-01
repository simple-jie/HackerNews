package com.simple_jie.data.dao;

import android.text.TextUtils;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xingbo.Jie on 1/3/17.
 */

public class IntegerListConverter implements PropertyConverter<List<Integer>, String> {
    @Override
    public List<Integer> convertToEntityProperty(String databaseValue) {
        List<Integer> result = new ArrayList<>();
        if (!TextUtils.isEmpty(databaseValue)) {
            String[] values = databaseValue.split(",");
            for (String value : values) {
                if (!TextUtils.isEmpty(value)) {
                    result.add(Integer.valueOf(value));
                }
            }
        }
        return result;
    }

    @Override
    public String convertToDatabaseValue(List<Integer> entityProperty) {
        if (entityProperty != null && entityProperty.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : entityProperty) {
                sb.append(integer);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        return null;
    }
}
