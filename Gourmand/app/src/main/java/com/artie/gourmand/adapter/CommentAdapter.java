package com.artie.gourmand.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.ProfileActivity;
import com.artie.gourmand.dao.CommentItemCollectionDao;
import com.artie.gourmand.dao.CommentItemDao;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by ANFIELD on 9/6/2560.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context mContext;
    private CommentItemCollectionDao mDao;
    private OnItemClickListener mOnItemClickListener;

    public CommentAdapter(Context context, CommentItemCollectionDao dao, OnItemClickListener onItemClickListener) {
        mContext = context;
        mDao = dao;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItemClickListener(mOnItemClickListener);
        holder.setComment(mDao.getComments().get(position));
    }

    @Override
    public int getItemCount() {
        return mDao.getComments().size();
    }

    public void setDao(CommentItemCollectionDao dao) {
        mDao = dao;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mUserImage;
        TextView mUserName;
        TextView mComment;

        private OnItemClickListener mOnItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            mUserImage = (ImageView) itemView.findViewById(R.id.image_user);
            mUserName = (TextView) itemView.findViewById(R.id.text_username);
            mComment = (TextView) itemView.findViewById(R.id.text_comment);

            mUserImage.setOnClickListener(this);
            mUserName.setOnClickListener(this);
            itemView.findViewById(R.id.text_username).setOnClickListener(this);
        }

        public void setItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_user:
                case R.id.text_username:
                    presentProfileScreen(mDao.getComments().get(getLayoutPosition()).getMember().getId());
                    break;
                default:
                    mOnItemClickListener.onItemClick(v, getLayoutPosition());
                    break;
            }
        }

        public void setComment(CommentItemDao comment) {
            mUserName.setText(comment.getMember().getName());
            mComment.setText(comment.getText());

            Glide.with(mContext)
                    .load(comment.getMember().getAvatarUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.avatar_placeholder))
                    .into(mUserImage);
        }

        private void presentProfileScreen(int memberID) {
            Intent intent = ProfileActivity.getStartIntent(mContext, memberID);
            mContext.startActivity(intent);
        }
    }

}
