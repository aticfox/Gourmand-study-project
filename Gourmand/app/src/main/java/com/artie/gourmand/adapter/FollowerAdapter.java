package com.artie.gourmand.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artie.gourmand.R;

/**
 * Created by ANFIELD on 2/6/2560.
 */

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText("Follower, Position: " + position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewPosition = (TextView) itemView.findViewById(R.id.text_follower);
        }

        public void setText(String text) {
            mTextViewPosition.setText(text);
        }
    }

}