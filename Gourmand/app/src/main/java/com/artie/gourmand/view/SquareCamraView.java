package com.artie.gourmand.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

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

}
