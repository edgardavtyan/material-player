package com.edavtyan.materialplayer.transition;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;

import com.ed.libsutils.utils.ViewUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Setter;

public class SourceSharedViews {
	private final List<Pair<View, String>> sharedViews;

	private @Setter OnEnterAnimationEndListener onEnterAnimationEndListener;

	public interface OnEnterAnimationEndListener {
		void onEnterAnimationEnd();
	}

	@SafeVarargs
	public SourceSharedViews(Pair<View, String>... sharedViews) {
		this.sharedViews = new ArrayList<>(Arrays.asList(sharedViews));
	}

	public Bundle build() {
		Bundle bundle = new Bundle();
		ArrayList<String> transitionNames = new ArrayList<>(sharedViews.size());

		for (Pair<View, String> pair : sharedViews) {
			View view = pair.first;
			String transitionName = pair.second;
			int[] viewLocation = ViewUtils.getLocationOnScreen(pair.first);
			transitionNames.add(transitionName);
			bundle.putFloat(transitionName + SharedTransitionsManager.PARAM_X, viewLocation[0]);
			bundle.putFloat(transitionName + SharedTransitionsManager.PARAM_Y, viewLocation[1]);
			bundle.putInt(transitionName + SharedTransitionsManager.PARAM_WIDTH, view.getWidth());
			bundle.putInt(transitionName + SharedTransitionsManager.PARAM_HEIGHT, view.getHeight());
		}

		bundle.putStringArrayList(SharedTransitionsManager.EXTRA_TRANSITION_NAMES, transitionNames);
		return bundle;
	}

	public void show() {
		for (Pair<View, String> pair : sharedViews) {
			pair.first.setVisibility(View.VISIBLE);
		}
	}

	public View find(String transitionName) {
		for (Pair<View, String> sharedView : sharedViews) {
			if (sharedView.second.equals(transitionName)) {
				return sharedView.first;
			}
		}

		return null;
	}

	public void add(Pair<View, String> sharedPair) {
		sharedViews.add(sharedPair);
	}

	public void raiseOnEnterTransitionEndListener() {
		if (onEnterAnimationEndListener != null) {
			onEnterAnimationEndListener.onEnterAnimationEnd();
		}
	}

	public Class getActivityClass() {
		return sharedViews.get(0).first.getContext().getClass();
	}
}
