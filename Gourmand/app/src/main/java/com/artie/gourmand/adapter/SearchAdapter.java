package com.artie.gourmand.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANFIELD on 25/6/2560.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<User> mUsers = new ArrayList<>();

    public SearchAdapter(List<User> users) {
        mUsers = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        holder.setUser(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mUserImage;
        TextView mUserName;

        public ViewHolder(View itemView) {
            super(itemView);

            mUserImage = (ImageView) itemView.findViewById(R.id.image_user);
            mUserName = (TextView) itemView.findViewById(R.id.text_username);
        }

        public void setUser(User user) {
            mUserImage.setImageResource(user.getUserImageID());
            mUserName.setText(user.getUserName());
        }
    }

}
