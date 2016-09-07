package com.edavtyan.materialplayer.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.lib.testable.TestableFragment;

public class BaseFragment extends TestableFragment {
	protected App app;

	@SuppressWarnings("unchecked")
	public <T> T findView(View rootView, int resId) {
		return (T) rootView.findViewById(resId);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (App) getContext().getApplicationContext();
	}
}
