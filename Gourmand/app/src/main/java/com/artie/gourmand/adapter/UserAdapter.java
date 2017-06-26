package com.artie.gourmand.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANFIELD on 2/6/2560.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> mUsers = new ArrayList<>();

    public UserAdapter(List<User> users) {
        mUsers = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setUser(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mUserImage;
        TextView mUserName;
        Button mFollowButton;

        public ViewHolder(View itemView) {
            super(itemView);

            mUserImage = (ImageView) itemView.findViewById(R.id.image_user);
            mUserName = (TextView) itemView.findViewById(R.id.text_username);
            mFollowButton = (Button) itemView.findViewById(R.id.button_follow);
        }

        public void setUser(User user) {
            mUserImage.setImageResource(user.getUserImageID());
            mUserName.setText(user.getUserName());
            mFollowButton.setText(followButtonTitle(user.isFollowing()));
        }

        private String followButtonTitle(Boolean isFollowing) {
            return isFollowing ? "Following" : "Follow";
        }
    }

}