package com.artie.gourmand.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.artie.gourmand.R;

/**
 * Created by ANFIELD on 24/5/2560.
 */

public class FeedItem extends BaseCustomViewGroup{

    ImageView mImageViewPost;

    public FeedItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public FeedItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public FeedItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    public FeedItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.item_feed, this);
    }

    private void initInstances() {
        mImageViewPost = (ImageView) findViewById(R.id.image_post);
    }

}
