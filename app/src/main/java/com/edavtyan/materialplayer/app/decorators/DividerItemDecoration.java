package com.edavtyan.materialplayer.app.decorators;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final String DIVIDER_COLOR = "#cccccc";
    private static final float DIVIDER_WIDTH = 1f;

    private Paint dividerPaint;

    public DividerItemDecoration() {
        dividerPaint = new Paint();
        dividerPaint.setColor(Color.parseColor(DIVIDER_COLOR));
        dividerPaint.setStrokeWidth(DIVIDER_WIDTH);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            c.drawLine(0, child.getTop(), child.getRight(), child.getTop(), dividerPaint);
            c.save();
        }
    }
}