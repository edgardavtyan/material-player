package com.edavtyan.materialplayer.transition;

import android.os.Bundle;
import android.view.View;

import com.ed.libsutils.utils.ViewUtils;

public class SourceSharedViews {
	private final View[] sharedViews;

	public SourceSharedViews(View... sharedViews) {
		this.sharedViews = sharedViews;
	}

	public Bundle build() {
		Bundle bundle = new Bundle();

		for (View view : sharedViews) {
			String viewIdName = view.getResources().getResourceEntryName(view.getId());
			int[] viewLocation = ViewUtils.getLocationOnScreen(view);
			bundle.putFloat(viewIdName + ":x", viewLocation[0]);
			bundle.putFloat(viewIdName + ":y", viewLocation[1]);
			bundle.putInt(viewIdName + ":width", view.getWidth());
			bundle.putInt(viewIdName + ":height", view.getHeight());
		}

		return bundle;
	}

	public void hide() {
		for (View view : sharedViews) {
			view.setVisibility(View.INVISIBLE);
		}
	}

	public void show() {
		for (View view : sharedViews) {
			view.setVisibility(View.VISIBLE);
		}
	}
}
