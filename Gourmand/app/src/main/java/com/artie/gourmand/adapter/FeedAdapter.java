package com.artie.gourmand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.dao.PostItemCollectionDao;
import com.artie.gourmand.dao.PostItemDao;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by ANFIELD on 24/5/2560.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>  {

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private PostItemCollectionDao mDao;

    public FeedAdapter(Context context, PostItemCollectionDao dao, OnItemClickListener onItemClickListener) {
        mContext = context;
        mOnItemClickListener = onItemClickListener;
        mDao = dao;
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedAdapter.ViewHolder holder, int position) {
        holder.setItemClickListener(mOnItemClickListener);
        holder.setPost(mDao.getPosts().get(position));
    }

    @Override
    public int getItemCount() {
        return mDao.getPosts().size();
    }

    public void setDao(PostItemCollectionDao dao) {
        mDao = dao;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClickListener mOnItemClickListener;

        ImageView mUserImage;
        ImageView mPostImage;
        TextView mUserName;
        TextView mCaption;
        TextView mCreateTime;
        TextView mLocationName;
        TextView mLikeCount;

        public ViewHolder(View itemView) {
            super(itemView);

            mUserImage = (ImageView) itemView.findViewById(R.id.image_user);
            mPostImage = (ImageView) itemView.findViewById(R.id.image_post);
            mUserName = (TextView) itemView.findViewById(R.id.text_username);
            mCaption = (TextView) itemView.findViewById(R.id.caption);
            mCreateTime = (TextView) itemView.findViewById(R.id.text_create_time);
            mLocationName = (TextView) itemView.findViewById(R.id.text_location_name);
            mLikeCount = (TextView) itemView.findViewById(R.id.text_like_count);

            itemView.findViewById(R.id.text_location_name).setOnClickListener(this);
            itemView.findViewById(R.id.button_comment).setOnClickListener(this);
        }

        public void setItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClick(v, getLayoutPosition());
        }

        public void setPost(PostItemDao post) {
            mUserName.setText(post.getMember().getName());
            mCaption.setText(post.getCaption());
            mLocationName.setText(post.getLocationName());
            mCreateTime.setText(post.getCreateTimeText());
            mLikeCount.setText("Liked "+post.getLikeCount().toString());

            Glide.with(mContext)
                    .load(post.getMember().getAvatarUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.avatar_placeholder))
                    .into(mUserImage);
            Glide.with(mContext)
                    .load(post.getImageURL())
                    .apply(RequestOptions.placeholderOf(R.drawable.post_placeholder))
                    .into(mPostImage);
        }
    }

}
