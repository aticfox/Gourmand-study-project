package com.artie.gourmand.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.artie.gourmand.R;
import com.artie.gourmand.activity.ProfileActivity;
import com.artie.gourmand.dao.MemberItemCollectionDao;
import com.artie.gourmand.dao.MemberItemDao;
import com.artie.gourmand.manager.HttpManager;
import com.artie.gourmand.model.User;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANFIELD on 2/6/2560.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private MemberItemCollectionDao mDao;

    private boolean isLoadingFollowing = false;

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
            mUserImage.setOnClickListener(this);
            mUserName.setOnClickListener(this);
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
            return isFollowing ? "Unfollow" : "Follow";
        }

        @Override
        public void onClick(View v) {
            MemberItemDao member = mDao.getMembers().get(getLayoutPosition());

            switch (v.getId()) {
                case R.id.button_follow:
                    if (member.isFollowing()) {
                        unfollow(member);
                    } else {
                        follow(member);
                    }
                    isLoadingFollowing = true;
                    break;
                case R.id.image_user:
                case R.id.text_username:
                    presentProfileScreen(member.getId());
                    break;
                default:
                    break;
            }
        }

        private void presentProfileScreen(int memberID) {
            Intent intent = ProfileActivity.getStartIntent(mContext, memberID);
            mContext.startActivity(intent);
        }

        private void unfollow(final MemberItemDao member) {
            if (isLoadingFollowing) {
                return;
            }

            Call<Void> call = HttpManager.getInstance()
                    .getService()
                    .unfollow(member.getId(), User.getInstance().getDao().getId());
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        member.setFollowing(false);
                        isLoadingFollowing = false;

                        setUser(member);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("unfollow", "Fail load data: " + t.toString());
                }
            });
        }

        private void follow(final MemberItemDao member) {
            if (isLoadingFollowing) {
                return;
            }

            Call<Void> call = HttpManager.getInstance()
                    .getService()
                    .follow(member.getId(), User.getInstance().getDao().getId());
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        member.setFollowing(true);
                        isLoadingFollowing = false;

                        setUser(member);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("follow", "Fail load data: " + t.toString());
                }
            });
        }
    }

}
