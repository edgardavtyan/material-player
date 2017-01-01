package com.edavtyan.prefs.color;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ColorCircleView extends View {
	private final Paint paint;
	private final ColorCircleAttributes attrs;

	public ColorCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.attrs = new ColorCircleAttributes(context, attrs);
		this.paint = initPaint();
		if (isInEditMode()) setColor(Color.RED);
	}

	public ColorCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.attrs = new ColorCircleAttributes(context, attrs);
		this.paint = initPaint();
		if (isInEditMode()) setColor(Color.RED);
	}

	public int getColor() {
		return paint.getColor();
	}

	public void setColor(int color) {
		paint.setColor(color);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int center = getWidth() / 2;
		canvas.drawCircle(center, center, center, paint);
	}

	private Paint initPaint() {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(attrs.getBackgroundColor());
		return paint;
	}
}
