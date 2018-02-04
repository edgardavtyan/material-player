package com.edavtyan.materialplayer.transition;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;

import com.ed.libsutils.utils.ViewUtils;

import java.util.ArrayList;

public class SourceSharedViews {
	private final Pair<View, String>[] sharedViews;

	@SafeVarargs
	public SourceSharedViews(Pair<View, String>... sharedViews) {
		this.sharedViews = sharedViews;
	}

	public Bundle build() {
		Bundle bundle = new Bundle();
		ArrayList<String> transitionNames = new ArrayList<>(sharedViews.length);

		for (Pair<View, String> pair : sharedViews) {
			View view = pair.first;
			String transitionName = pair.second;
			int[] viewLocation = ViewUtils.getLocationOnScreen(pair.first);
			transitionNames.add(transitionName);
			bundle.putFloat(transitionName + ":x", viewLocation[0]);
			bundle.putFloat(transitionName + ":y", viewLocation[1]);
			bundle.putInt(transitionName + ":width", view.getWidth());
			bundle.putInt(transitionName + ":height", view.getHeight());
		}

		bundle.putStringArrayList("transitionNames", transitionNames); ;
		return bundle;
	}

	public void hide() {
		for (Pair<View, String> pair : sharedViews) {
			pair.first.setVisibility(View.INVISIBLE);
		}
	}

	public void show() {
		for (Pair<View, String> pair : sharedViews) {
			pair.first.setVisibility(View.VISIBLE);
		}
	}
}
