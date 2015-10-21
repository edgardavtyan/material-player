package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareView  extends ImageView {
    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
