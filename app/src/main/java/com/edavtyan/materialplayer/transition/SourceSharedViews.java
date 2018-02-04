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
			bundle.putFloat(transitionName + SharedViewsTransition.PARAM_X, viewLocation[0]);
			bundle.putFloat(transitionName + SharedViewsTransition.PARAM_Y, viewLocation[1]);
			bundle.putInt(transitionName + SharedViewsTransition.PARAM_WIDTH, view.getWidth());
			bundle.putInt(transitionName + SharedViewsTransition.PARAM_HEIGHT, view.getHeight());
		}

		bundle.putStringArrayList(SharedViewsTransition.EXTRA_TRANSITION_NAMES, transitionNames);
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

	public View[] getViews() {
		View[] views = new View[sharedViews.length];
		for (int i = 0; i < sharedViews.length; i++) {
			views[i] = sharedViews[i].first;
		}
		return views;
	}
}
