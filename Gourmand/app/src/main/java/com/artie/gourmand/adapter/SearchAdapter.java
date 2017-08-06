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
import com.artie.gourmand.dao.MemberItemCollectionDao;
import com.artie.gourmand.dao.MemberItemDao;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by ANFIELD on 25/6/2560.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context mContext;
    private MemberItemCollectionDao mDao;

    public SearchAdapter(Context context, MemberItemCollectionDao dao) {
        mContext = context;
        mDao = dao;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        holder.setMember(mDao.getMembers().get(position));
    }

    public void setDao(MemberItemCollectionDao dao) {
        mDao = dao;
    }

    @Override
    public int getItemCount() {
        return mDao.getMembers().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mUserImage;
        TextView mUserName;

        public ViewHolder(View itemView) {
            super(itemView);

            mUserImage = (ImageView) itemView.findViewById(R.id.image_user);
            mUserName = (TextView) itemView.findViewById(R.id.text_username);

            mUserImage.setOnClickListener(this);
            mUserName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_user:
                case R.id.text_username:
                    presentProfileScreen(mDao.getMembers().get(getLayoutPosition()).getId());
                    break;
                default:
                    break;
            }
        }

        private void presentProfileScreen(int memberID) {
            Intent intent = ProfileActivity.getStartIntent(mContext, memberID);
            mContext.startActivity(intent);
        }

        public void setMember(MemberItemDao member) {
            mUserName.setText(member.getName());

            Glide.with(mContext)
                    .load(member.getAvatarUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.avatar_placeholder))
                    .into(mUserImage);
        }
    }

}
