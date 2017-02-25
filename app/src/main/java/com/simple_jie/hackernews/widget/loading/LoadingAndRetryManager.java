package com.simple_jie.hackernews.widget.loading;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhy on 15/8/27.
 */
public class LoadingAndRetryManager
{
    public static final int NO_LAYOUT_ID = 0;
    public static int BASE_LOADING_LAYOUT_ID = NO_LAYOUT_ID;
    public static int BASE_RETRY_LAYOUT_ID = NO_LAYOUT_ID;
    public static int BASE_EMPTY_LAYOUT_ID = NO_LAYOUT_ID;

    public LoadingAndRetryLayout mLoadingAndRetryLayout;

    public STATE currentState = STATE.CONTENT;

    public enum STATE {
        LOADING,
        EMPTY,
        RETRY,
        CONTENT
    }


    public OnLoadingAndRetryListener DEFAULT_LISTENER = new OnLoadingAndRetryListener()
    {
        @Override
        public void setRetryEvent(View retryView)
        {

        }
    };


    public LoadingAndRetryManager(Object activityOrFragmentOrView, OnLoadingAndRetryListener listener)
    {
        if (listener == null) listener = DEFAULT_LISTENER;

        ViewGroup contentParent = null;
        Context context;
        if (activityOrFragmentOrView instanceof Activity)
        {
            Activity activity = (Activity) activityOrFragmentOrView;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (activityOrFragmentOrView instanceof Fragment)
        {
            Fragment fragment = (Fragment) activityOrFragmentOrView;
            context = fragment.getActivity();
            contentParent = (ViewGroup) (fragment.getView().getParent());
        } else if (activityOrFragmentOrView instanceof View)
        {
            View view = (View) activityOrFragmentOrView;
            contentParent = (ViewGroup) (view.getParent());
            context = view.getContext();
        } else
        {
            throw new IllegalArgumentException("the argument's type must be Fragment or Activity: init(context)");
        }

        //get contentParent
        int index = 0;
        View oldContent;
        if (activityOrFragmentOrView instanceof View)
        {
            oldContent = (View) activityOrFragmentOrView;
            if (contentParent != null) { // for viewPager
                int childCount = contentParent.getChildCount();
                for (int i = 0; i < childCount; i++)
                {
                    if (contentParent.getChildAt(i) == oldContent)
                    {
                        index = i;
                        break;
                    }
                }
            }
        } else
        {
            oldContent = contentParent.getChildAt(0);
        }

        if (contentParent != null) { // for viewPager
            contentParent.removeView(oldContent);
        }

        //setup content layout
        LoadingAndRetryLayout loadingAndRetryLayout = new LoadingAndRetryLayout(context);
        if (contentParent != null) { // for viewPager
            ViewGroup.LayoutParams lp = oldContent.getLayoutParams();
            contentParent.addView(loadingAndRetryLayout, index, lp);
        }

        loadingAndRetryLayout.setContentView(oldContent);
        // setup loading,retry,empty layout
        setupLoadingLayout(listener, loadingAndRetryLayout);
        setupRetryLayout(listener, loadingAndRetryLayout);
        setupEmptyLayout(listener, loadingAndRetryLayout);
        //callback
        listener.setRetryEvent(loadingAndRetryLayout.getRetryView());
        listener.setLoadingEvent(loadingAndRetryLayout.getLoadingView());
        listener.setEmptyEvent(loadingAndRetryLayout.getEmptyView());
        mLoadingAndRetryLayout = loadingAndRetryLayout;
    }

    private void setupEmptyLayout(OnLoadingAndRetryListener listener, LoadingAndRetryLayout loadingAndRetryLayout)
    {
        View emptyView = null;

        if (listener.isSetEmptyLayout())
        {
            int layoutId = listener.generateEmptyLayoutId();
            if (layoutId != NO_LAYOUT_ID)
            {
                emptyView = loadingAndRetryLayout.setEmptyView(layoutId);
            } else
            {
                emptyView = loadingAndRetryLayout.setEmptyView(listener.generateEmptyLayout());
            }
        } else
        {
            if (BASE_EMPTY_LAYOUT_ID != NO_LAYOUT_ID)
                emptyView = loadingAndRetryLayout.setEmptyView(BASE_EMPTY_LAYOUT_ID);
        }

//        if (emptyView != null)
//            listener.onBindEmptyView(emptyView);
    }

    private void setupLoadingLayout(OnLoadingAndRetryListener listener, LoadingAndRetryLayout loadingAndRetryLayout)
    {
        View loadingView = null;
        if (listener.isSetLoadingLayout())
        {
            int layoutId = listener.generateLoadingLayoutId();
            if (layoutId != NO_LAYOUT_ID)
            {
                loadingView = loadingAndRetryLayout.setLoadingView(layoutId);
            } else
            {
                loadingView = loadingAndRetryLayout.setLoadingView(listener.generateLoadingLayout());
            }
        } else
        {
            if (BASE_LOADING_LAYOUT_ID != NO_LAYOUT_ID)
                loadingView = loadingAndRetryLayout.setLoadingView(BASE_LOADING_LAYOUT_ID);
        }

//        if (loadingView != null)
//            listener.onBindLoadingView(loadingView);
    }

    private void setupRetryLayout(OnLoadingAndRetryListener listener, LoadingAndRetryLayout loadingAndRetryLayout)
    {
        View retryView = null;
        if (listener.isSetRetryLayout())
        {
            int layoutId = listener.generateRetryLayoutId();
            if (layoutId != NO_LAYOUT_ID)
            {
                retryView = loadingAndRetryLayout.setLoadingView(layoutId);
            } else
            {
                retryView = loadingAndRetryLayout.setLoadingView(listener.generateRetryLayout());
            }
        } else
        {
            if (BASE_RETRY_LAYOUT_ID != NO_LAYOUT_ID)
                retryView = loadingAndRetryLayout.setRetryView(BASE_RETRY_LAYOUT_ID);
        }

//        if (retryView != null)
//            listener.onBindRetryView(retryView);
    }

    public static LoadingAndRetryManager generate(Object activityOrFragment, OnLoadingAndRetryListener listener)
    {
        return new LoadingAndRetryManager(activityOrFragment, listener);
    }

    public void showLoading()
    {
        currentState = STATE.LOADING;
        mLoadingAndRetryLayout.showLoading();
    }

    public void showRetry()
    {
        currentState = STATE.RETRY;
        mLoadingAndRetryLayout.showRetry();
    }

    public void showContent()
    {
        currentState = STATE.CONTENT;
        mLoadingAndRetryLayout.showContent();
    }

    public void showEmpty()
    {
        currentState = STATE.EMPTY;
        mLoadingAndRetryLayout.showEmpty();
    }

    /**
     * for view pager, View被回收之后 根据缓存的状态恢复之前的显示内容
     */
    public void restoreByState() {
        switch (currentState) {
            case LOADING:
                mLoadingAndRetryLayout.showLoading();
                break;
            case EMPTY:
                mLoadingAndRetryLayout.showEmpty();
                break;
            case RETRY:
                mLoadingAndRetryLayout.showRetry();
                break;
            case CONTENT:
                mLoadingAndRetryLayout.showContent();
                break;
        }
    }
}
