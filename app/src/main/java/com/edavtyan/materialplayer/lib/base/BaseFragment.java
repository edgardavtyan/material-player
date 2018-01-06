package com.edavtyan.materialplayer.lib.base;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.lib.testable.TestableFragment;

public class BaseFragment extends TestableFragment {
	public App getApp() {
		return (App) getActivity().getApplication();
	}
}
