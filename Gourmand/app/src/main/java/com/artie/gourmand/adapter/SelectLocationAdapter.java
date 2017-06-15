package com.artie.gourmand.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artie.gourmand.R;

/**
 * Created by ANFIELD on 31/5/2560.
 */

public class SelectLocationAdapter extends RecyclerView.Adapter<SelectLocationAdapter.ViewHolder> {

    private String[] mLocationNames;

    public SelectLocationAdapter(String[] locations) {
        mLocationNames = locations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setLocationName(mLocationNames[position]);
    }

    @Override
    public int getItemCount() {
        return mLocationNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mLocationName;

        public ViewHolder(View itemView) {
            super(itemView);
            mLocationName = (TextView) itemView.findViewById(R.id.text_location_name);
        }

        public void setLocationName(String locationName) {
            mLocationName.setText(locationName);
        }
    }

}
