package com.artie.gourmand.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ANFIELD on 24/5/2560.
 */

public class FeedAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view;

        if (convertView == null) {
            view = new TextView(parent.getContext());
        } else {
            view = (TextView) convertView;
        }

        view.setText(String.format("Post %d", position));

        return view;
    }

}
