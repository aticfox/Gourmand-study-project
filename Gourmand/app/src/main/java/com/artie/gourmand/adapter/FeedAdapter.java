package com.artie.gourmand.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.artie.gourmand.view.FeedItem;

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
        FeedItem item;

        if (convertView == null) {
            item = new FeedItem(parent.getContext());
        } else {
            item = (FeedItem) convertView;
        }

        return item;
    }

}
