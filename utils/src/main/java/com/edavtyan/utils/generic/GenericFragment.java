package com.edavtyan.utils.generic;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;

public class GenericFragment extends Fragment {
	@SuppressWarnings("unchecked")
	public <T> T findView(View rootView, @IdRes int resId) {
		return (T) rootView.findViewById(resId);
	}
}
