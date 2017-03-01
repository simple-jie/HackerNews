package com.simple_jie.data.entity.mapper;

import com.simple_jie.data.entity.ItemEntity;
import com.simple_jie.domain.entities.Item;

import java.util.ArrayList;

/**
 * Created by Xingbo.Jie on 1/3/17.
 */

public class EntityMapper {
    public static Item convert(ItemEntity itemEntity) {
        if (itemEntity == null)
            return null;

        return Item.newBuilder()
                .by(itemEntity.getBy())
                .descendants(itemEntity.getDescendants())
                .id(itemEntity.getItemId())
                .kids(itemEntity.getKids() == null ? new ArrayList<Integer>() : new ArrayList<>(itemEntity.getKids()))
                .parent(itemEntity.getParent())
                .parts(itemEntity.getParts() == null ? new ArrayList<Integer>() : new ArrayList<>(itemEntity.getParts()))
                .score(itemEntity.getScore())
                .text(itemEntity.getText())
                .time(itemEntity.getTime())
                .type(itemEntity.getType())
                .title(itemEntity.getTitle())
                .url(itemEntity.getUrl())
                .build();
    }
}
