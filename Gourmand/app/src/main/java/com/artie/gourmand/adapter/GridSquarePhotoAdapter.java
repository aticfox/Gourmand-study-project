package com.artie.gourmand.adapter;

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

    public GridSquarePhotoAdapter(int column) {
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

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

    }

}
