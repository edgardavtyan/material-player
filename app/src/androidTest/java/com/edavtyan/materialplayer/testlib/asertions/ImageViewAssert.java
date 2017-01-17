package com.edavtyan.materialplayer.testlib.asertions;

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

import com.edavtyan.materialplayer.lib.testable.TestableActivity;

import org.assertj.core.api.AbstractAssert;

public class ImageViewAssert extends AbstractAssert<ImageViewAssert, ImageView> {
	private final String imageViewIdName;
	private final Context context;

	public ImageViewAssert(ImageView actual, Class<?> selfType) {
		super(actual, selfType);
		context = InstrumentationRegistry.getTargetContext();

		if (actual.getId() == -1) {
			imageViewIdName = null;
		} else {
			imageViewIdName = context.getResources().getResourceEntryName(actual.getId());
		}
	}

	public static ImageViewAssert assertThatImageView(View view, @IdRes int imageViewId) {
		ImageView imageView = (ImageView) view.findViewById(imageViewId);
		return new ImageViewAssert(imageView, ImageViewAssert.class);
	}

	public static ImageViewAssert assertThatImageView(TestableActivity activity, @IdRes int imageViewId) {
		ImageView imageView = activity.findView(imageViewId);
		return new ImageViewAssert(imageView, ImageViewAssert.class);
	}

	public static ImageViewAssert assertThatImageView(ImageView imageView) {
		return new ImageViewAssert(imageView, ImageViewAssert.class);
	}

	public ImageViewAssert hasDrawableWithId(@DrawableRes int drawableRes) {
		Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
		if (!areDrawablesIdentical(actual.getDrawable(), drawable)) {
			String drawableId = context.getResources().getResourceEntryName(drawableRes);
			if (imageViewIdName == null) {
				failWithMessage("Expected ImageView to have drawable with id='%s'", drawableId);
			} else {
				failWithMessage(
						"Expected ImageView with id='%s' to have drawable with id='%s'",
						imageViewIdName, drawableId);
			}
		}

		return this;
	}

	public ImageViewAssert hasBitmap(Bitmap art) {
		if (!getBitmap(actual.getDrawable()).sameAs(art)) {
			if (imageViewIdName == null) {
				failWithMessage("Expected ImageView to have bitmap '%s'", art);
			} else {
				failWithMessage(
						"Expected ImageView with id='%s' to have bitmap '%s'",
						imageViewIdName, art);
			}
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