package com.edavtyan.prefs.color;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.edavtyan.prefs.utils.PixelConverter;

public class ColorCircleView extends View {
	private static final float STROKE_WIDTH = 1.0f;

	private final Paint backgroundPaint;
	private final Paint borderPaint;
	private final ColorCircleAttributes attrs;

	public ColorCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.attrs = new ColorCircleAttributes(context, attrs);
		this.backgroundPaint = initBackgroundPaint();
		this.borderPaint = initBorderPaint();

		if (isInEditMode()) setColor(Color.RED);
	}

	public ColorCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.attrs = new ColorCircleAttributes(context, attrs);
		this.backgroundPaint = initBackgroundPaint();
		this.borderPaint = initBorderPaint();
		if (isInEditMode()) setColor(Color.RED);
	}

	public int getColor() {
		return backgroundPaint.getColor();
	}

	public void setColor(int color) {
		backgroundPaint.setColor(color);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int center = getWidth() / 2;
		canvas.drawCircle(center, center, center, backgroundPaint);
		canvas.drawCircle(center, center, center - 1, borderPaint);
	}

	private Paint initBorderPaint() {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(PixelConverter.dpToPx(STROKE_WIDTH));
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		return paint;
	}

	private Paint initBackgroundPaint() {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(attrs.getBackgroundColor());
		return paint;
	}
}
