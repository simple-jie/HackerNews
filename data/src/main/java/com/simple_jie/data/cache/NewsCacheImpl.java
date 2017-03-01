package com.simple_jie.data.cache;

import android.content.Context;

import com.simple_jie.data.entity.CategoryNewsEntity;
import com.simple_jie.data.entity.CategoryNewsEntityDao;
import com.simple_jie.data.entity.DaoMaster;
import com.simple_jie.data.entity.DaoSession;
import com.simple_jie.data.entity.ItemEntity;
import com.simple_jie.data.entity.ItemEntityDao;
import com.simple_jie.domain.entities.Category;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Xingbo.Jie on 1/3/17.
 */

@Singleton
public class NewsCacheImpl implements NewsCache {

    private DaoSession daoSession;
    private ItemEntityDao itemDao;
    private CategoryNewsEntityDao newsDao;

    @Inject
    public NewsCacheImpl(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "news-item-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        itemDao = daoSession.getItemEntityDao();
        newsDao = daoSession.getCategoryNewsEntityDao();
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

    @Override
    public void insertOrUpdate(CategoryNewsEntity categoryNews) {
        newsDao.insertOrReplace(categoryNews);
    }

    @Override
    public Observable<CategoryNewsEntity> loadNews(Category category) {
        return newsDao.queryBuilder()
                .where(CategoryNewsEntityDao.Properties.Category.eq(category))
                .rx()
                .unique();
    }
}
