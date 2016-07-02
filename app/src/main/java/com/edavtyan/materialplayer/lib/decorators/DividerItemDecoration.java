package com.edavtyan.materialplayer.lib.decorators;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
	private static final float DIVIDER_WIDTH = 1f;

	private Paint dividerPaint;

	public DividerItemDecoration(int color) {
		dividerPaint = new Paint();
		dividerPaint.setColor(color);
		dividerPaint.setStrokeWidth(DIVIDER_WIDTH);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		for (int i = 0; i < parent.getChildCount(); i++) {
			View child = parent.getChildAt(i);
			c.drawLine(0, child.getTop(), child.getRight(), child.getTop(), dividerPaint);
			c.drawLine(0, child.getBottom(), child.getRight(), child.getBottom(), dividerPaint);
			c.save();
		}
	}
}
