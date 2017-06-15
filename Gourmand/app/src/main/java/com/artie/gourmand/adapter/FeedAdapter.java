package com.artie.gourmand.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANFIELD on 24/5/2560.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>  {

    private OnItemClickListener mOnItemClickListener;

    private List<Post> mPosts = new ArrayList<>();

    public FeedAdapter(List<Post> posts, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        mPosts = posts;
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedAdapter.ViewHolder holder, int position) {
        holder.setItemClickListener(mOnItemClickListener);
        holder.setPost(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClickListener mOnItemClickListener;

        ImageView mUserImage;
        ImageView mPostImage;
        TextView mUserName;

        public ViewHolder(View itemView) {
            super(itemView);

            mUserImage = (ImageView) itemView.findViewById(R.id.image_user);
            mPostImage = (ImageView) itemView.findViewById(R.id.image_post);
            mUserName = (TextView) itemView.findViewById(R.id.text_username);

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

        public void setPost(Post post) {
            mUserImage.setImageResource(post.getUserImageID());
            mPostImage.setImageResource(post.getPostImageID());
            mUserName.setText(post.getUserName());
        }
    }

}
