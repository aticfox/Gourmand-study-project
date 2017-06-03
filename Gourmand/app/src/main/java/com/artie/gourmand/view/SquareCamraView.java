package com.artie.gourmand.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.artie.gourmand.R;
import com.flurgle.camerakit.CameraView;

/**
 * Created by ANFIELD on 27/5/2560.
 */

public class SquareCamraView extends CameraView {

    public SquareCamraView(@NonNull Context context) {
        super(context);
    }

    public SquareCamraView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

    /**
     * Created by ANFIELD on 2/6/2560.
     */

    public static class PhotoItem extends BaseCustomViewGroup {

        ImageView mImageViewPhoto;

        public PhotoItem(Context context) {
            super(context);
        }

        public PhotoItem(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public PhotoItem(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public PhotoItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            initInflate();
            initInstances();
        }

        private void initInflate() {
            inflate(getContext(), R.layout.item_photo, this);
        }

        private void initInstances() {
            mImageViewPhoto = (ImageView) findViewById(R.id.image_photo);
        }

    }
}
