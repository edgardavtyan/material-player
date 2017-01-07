package com.edavtyan.materialplayer.testlib.asertions;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import org.assertj.core.api.AbstractAssert;

public class ImageViewAssert extends AbstractAssert<ImageViewAssert, ImageView> {
	private final String imageViewIdName;
	private final Context context;

	public ImageViewAssert(@IdRes int imageViewId, ImageView actual, Class<?> selfType) {
		super(actual, selfType);
		context = InstrumentationRegistry.getTargetContext();
		imageViewIdName = context.getResources().getResourceEntryName(imageViewId);
	}

	public static ImageViewAssert assertImageView(View view, @IdRes int imageViewId) {
		ImageView imageView = (ImageView) view.findViewById(imageViewId);
		return new ImageViewAssert(imageViewId, imageView, ImageViewAssert.class);
	}

	public static ImageViewAssert assertImageView(Activity activity, @IdRes int imageViewId) {
		ImageView imageView = (ImageView) activity.findViewById(imageViewId);
		return new ImageViewAssert(imageViewId, imageView, ImageViewAssert.class);
	}

	public ImageViewAssert hasDrawableWithId(@DrawableRes int drawableRes) {
		Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
		if (!areDrawablesIdentical(actual.getDrawable(), drawable)) {
			failWithMessage(
					"Expected ImageView with id='%s' to have drawable with id='%s'",
					imageViewIdName, context.getResources().getResourceEntryName(drawableRes));
		}

		return this;
	}

	public ImageViewAssert hasBitmap(Bitmap art) {
		if (!getBitmap(actual.getDrawable()).sameAs(art)) {
			failWithMessage(
					"Expected ImageView with id='%s' to have bitmap '%s'",
					imageViewIdName, art);
		}

		return this;
	}

	private boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
		return getBitmap(drawableA).sameAs(getBitmap(drawableB));
	}

	private Bitmap getBitmap(Drawable drawable) {
		Bitmap result;
		if (drawable instanceof BitmapDrawable) {
			result = ((BitmapDrawable) drawable).getBitmap();
		} else {
			int width = drawable.getIntrinsicWidth();
			int height = drawable.getIntrinsicHeight();
			// Some drawables have no intrinsic width - e.g. solid colours.
			if (width <= 0) {
				width = 1;
			}
			if (height <= 0) {
				height = 1;
			}

			result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(result);
			drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
			drawable.draw(canvas);
		}
		return result;
	}
}
