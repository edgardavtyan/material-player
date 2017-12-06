package com.edavtyan.materialplayer.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class BitmapResizer {
	public static Bitmap resize(Bitmap bitmap, int newSize) {
		Bitmap resizedBitmap = Bitmap.createBitmap(newSize, newSize, Bitmap.Config.ARGB_8888);

		float scaleX = newSize / (float) bitmap.getWidth();
		float scaleY = newSize / (float) bitmap.getHeight();
		float pivotX = 0;
		float pivotY = 0;

		Matrix scaleMatrix = new Matrix();
		scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

		Canvas canvas = new Canvas(resizedBitmap);
		canvas.setMatrix(scaleMatrix);
		canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

		return resizedBitmap;
	}
}
