package com.artie.gourmand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artie.gourmand.R;
import com.artie.gourmand.util.Utils;

/**
 * Created by ANFIELD on 25/5/2560.
 */

public class GridSquarePhotoAdapter extends RecyclerView.Adapter<GridSquarePhotoAdapter.ViewHolder> {

    private int mColumn;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public GridSquarePhotoAdapter(Context context, int column, OnItemClickListener onItemClickListener) {
        mContext = context;
        mOnItemClickListener = onItemClickListener;
        mColumn = column;
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
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClickListener mOnItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.findViewById(R.id.view_overlay).setOnClickListener(this);
        }

        public void setItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClick(this, getLayoutPosition());
        }
    }

}
