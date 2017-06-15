package com.artie.gourmand.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.artie.gourmand.R;
import com.artie.gourmand.util.Utils;

/**
 * Created by ANFIELD on 25/5/2560.
 */

public class GridSquarePhotoAdapter extends RecyclerView.Adapter<GridSquarePhotoAdapter.ViewHolder> {

    private int mColumn;
    private OnItemClickListener mOnItemClickListener;

    private int[] mPostImageIDs;

    public GridSquarePhotoAdapter(int[] postImageIDs, int column, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        mColumn = column;
        mPostImageIDs = postImageIDs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = Utils.getScreenWidth(parent.getContext()) / mColumn;

        return new GridSquarePhotoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItemClickListener(mOnItemClickListener);
        holder.setPostImageID(mPostImageIDs[position]);
    }

    @Override
    public int getItemCount() {
        return mPostImageIDs.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClickListener mOnItemClickListener;

        ImageView mPostImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mPostImage = (ImageView) itemView.findViewById(R.id.image_photo);

            itemView.findViewById(R.id.view_overlay).setOnClickListener(this);
        }

        public void setItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClick(v, getLayoutPosition());
        }

        public void setPostImageID(int postImageID) {
            mPostImage.setImageResource(postImageID);
        }
    }

}
