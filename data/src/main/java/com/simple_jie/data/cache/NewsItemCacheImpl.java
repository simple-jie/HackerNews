package com.simple_jie.data.cache;

import android.content.Context;

import com.simple_jie.data.entity.DaoMaster;
import com.simple_jie.data.entity.DaoSession;
import com.simple_jie.data.entity.ItemEntity;
import com.simple_jie.data.entity.ItemEntityDao;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Xingbo.Jie on 1/3/17.
 */

@Singleton
public class NewsItemCacheImpl implements NewsCache {

    private DaoSession daoSession;
    private ItemEntityDao itemDao;

    @Inject
    public NewsItemCacheImpl(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "news-item-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        itemDao = daoSession.getItemEntityDao();
    }

    @Override
    public void insertOrUpdate(ItemEntity itemEntity) {
        itemDao.insertOrReplace(itemEntity);
    }

    @Override
    public Observable<ItemEntity> loadItem(int id) {
        return itemDao.queryBuilder()
                .where(ItemEntityDao.Properties.ItemId.eq(id))
                .rx()
                .unique();
    }
}
