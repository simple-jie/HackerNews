package com.simple_jie.hackernews.screen.main;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.hackernews.screen.BasePresenter;
import com.simple_jie.hackernews.screen.BaseView;

import java.util.List;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public interface CategoryNewsContract {
    interface IView extends BaseView<IPresenter> {
        void showLoading();
        void renderData(List<NewsItem> data);
        void showEmpty();
    }

    interface IPresenter extends BasePresenter<IView> {

        void setCategroy(Category categroy);

        /**
         * fetch newest data from server
         */
        void refreshData();

        /**
         * load data from cache if exist or fetch from server
         */
        void loadData();

        void showDetail();
    }
}
