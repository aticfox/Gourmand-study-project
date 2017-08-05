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
import com.artie.gourmand.dao.MemberItemCollectionDao;
import com.artie.gourmand.dao.MemberItemDao;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by ANFIELD on 2/6/2560.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private MemberItemCollectionDao mDao;

    public UserAdapter(Context context, MemberItemCollectionDao dao) {
        mContext = context;
        mDao = dao;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setUser(mDao.getMembers().get(position));
    }

    @Override
    public int getItemCount() {
        return mDao.getMembers().size();
    }

    public void setDao(MemberItemCollectionDao dao) {
        mDao = dao;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mUserImage;
        TextView mUserName;
        Button mFollowButton;

        public ViewHolder(View itemView) {
            super(itemView);

            mUserImage = (ImageView) itemView.findViewById(R.id.image_user);
            mUserName = (TextView) itemView.findViewById(R.id.text_username);
            mFollowButton = (Button) itemView.findViewById(R.id.button_follow);
            mFollowButton.setOnClickListener(this);
        }

        public void setUser(MemberItemDao user) {
            Glide.with(mContext)
                    .load(user.getAvatarUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.avatar_placeholder))
                    .into(mUserImage);
            mUserName.setText(user.getName());
            mFollowButton.setText(followButtonTitle(user.isFollowing()));
        }

        private String followButtonTitle(Boolean isFollowing) {
            return isFollowing ? "Following" : "Follow";
        }

        @Override
        public void onClick(View v) {

        }
    }

}
