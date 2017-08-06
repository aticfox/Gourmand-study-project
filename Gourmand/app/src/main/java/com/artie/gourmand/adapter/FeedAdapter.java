package com.artie.gourmand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        ImageView mImageUser;
        ImageView mImagePost;
        TextView mTextUserName;
        TextView mTextCaption;
        TextView mTextCreateTime;
        TextView mTextLocationName;
        TextView mTextLikeCount;
        Button mButtonLike;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageUser = (ImageView) itemView.findViewById(R.id.image_user);
            mImagePost = (ImageView) itemView.findViewById(R.id.image_post);
            mTextUserName = (TextView) itemView.findViewById(R.id.text_username);
            mTextCaption = (TextView) itemView.findViewById(R.id.text_caption);
            mTextCreateTime = (TextView) itemView.findViewById(R.id.text_create_time);
            mTextLocationName = (TextView) itemView.findViewById(R.id.text_location_name);
            mTextLikeCount = (TextView) itemView.findViewById(R.id.text_like_count);
            mButtonLike = (Button) itemView.findViewById(R.id.button_like);
            mButtonLike.setOnClickListener(this);

            mImageUser.setOnClickListener(this);
            mTextUserName.setOnClickListener(this);
            mTextLocationName.setOnClickListener(this);
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
            mTextUserName.setText(post.getMember().getName());
            mTextCaption.setText(post.getCaption());
            mTextLocationName.setText(post.getLocationName());
            mTextCreateTime.setText(post.getCreateTimeText());
            mTextLikeCount.setText("Liked " + post.getLikeCount().toString());
            if (post.isLike()) {
                mButtonLike.setBackgroundResource(R.drawable.btn_like_select);
            } else {
                mButtonLike.setBackgroundResource(R.drawable.btn_like_normal);
            }

            Glide.with(mContext)
                    .load(post.getMember().getAvatarUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.avatar_placeholder))
                    .into(mImageUser);
            Glide.with(mContext)
                    .load(post.getImageURL())
                    .apply(RequestOptions.placeholderOf(R.drawable.post_placeholder))
                    .into(mImagePost);
        }
    }

}
