package com.simple_jie.data.net;

import com.simple_jie.data.entity.ItemEntity;
import com.simple_jie.domain.entities.Item;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public interface APIService {
    @GET("item/{id}.json")
    Observable<ItemEntity> item(@Path("id") int id);

    @GET("{category}.json")
    Observable<List<Integer>> news(@Path("category") String category);
}
