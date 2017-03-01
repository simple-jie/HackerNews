package com.simple_jie.hackernews.screen.main;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.simple_jie.domain.entities.Item;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.domain.interactor.DefaultSubscriber;
import com.simple_jie.domain.interactor.NewsItemUseCase;
import com.simple_jie.hackernews.R;
import com.simple_jie.hackernews.utility.TimeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by Xingbo.Jie on 27/2/17.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private final ArrayList<NewsItem> mData;
    private final HashMap<NewsItem, Subscription> requestsMap;
    private NewsItemUseCase useCase;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(NewsItem item);

        void onOpenUrlClick(String url);
    }

    @Inject
    public NewsListAdapter(NewsItemUseCase useCase) {
        mData = new ArrayList<>();
        requestsMap = new HashMap<>();
        this.useCase = useCase;
    }

    @Override
    public NewsListAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.NewsViewHolder holder, int position) {
        holder.bindData(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<NewsItem> data) {
        mData.clear();
        if (data != null && data.size() > 0) {
            mData.addAll(data);
        }

        notifyDataSetChanged();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.points)
        TextView points;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.go_external)
        ImageView goExternal;

        View root;

        public NewsViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            ButterKnife.bind(this, itemView);
//            int id = R.layout.item_news_list_layout;
        }

        public void bindData(final NewsItem news, final int postion) {
            Item item = news.getItem();
            title.setTag(news);
            if (item == null && !requestsMap.containsKey(news)) {
                useCase.execute(new DefaultSubscriber<Item>() {
                    @Override
                    public void onNext(Item item) {
                        super.onNext(item);
                        news.setItem(item);
                        requestsMap.remove(news);

                        if (news.equals(title.getTag())) {
                            notifyItemChanged(postion);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        requestsMap.remove(news);
                    }
                }, new NewsItemUseCase.Args(news.getId(), false));
            }

            updateUI(news, postion);
        }

        private void updateUI(final NewsItem news, final int postion) {
            final Item item = news.getItem();
            Resources resource = title.getContext().getResources();
            if (item == null) {
                title.setTextColor(resource.getColor(R.color.light_gary));
                title.setText(R.string.ellipsis_bulk);
                author.setText(R.string.ellipsis);
                points.setText(R.string.ellipsis);
                time.setText(R.string.ellipsis);
                root.setOnClickListener(null);
                goExternal.setOnClickListener(null);
            } else {
                title.setTextColor(resource.getColor(android.R.color.black));
                title.setText((postion + 1) + ". " + item.getTitle());
                author.setText(item.getBy());
                points.setText(String.format(resource.getString(R.string.format_points), item.getScore()));
                time.setText(TimeUtil.getNewsListTime(title.getContext(), item.getTime()));

                root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!requestsMap.containsKey(news)) {
                            useCase.execute(new DefaultSubscriber<Item>() {
                                @Override
                                public void onNext(Item item) {
                                    super.onNext(item);
                                    news.setItem(item);
                                    requestsMap.remove(news);

                                    if (news.equals(title.getTag())) {
                                        Toast.makeText(title.getContext(), R.string.item_updated_hint, Toast.LENGTH_SHORT).show();
                                        notifyItemChanged(postion);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    requestsMap.remove(news);
                                }
                            }, new NewsItemUseCase.Args(news.getId(), true));
                        }

                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(news);
                        }
                    }
                });
                goExternal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onOpenUrlClick(item.getUrl());
                        }
                    }
                });
            }
        }
    }
}
