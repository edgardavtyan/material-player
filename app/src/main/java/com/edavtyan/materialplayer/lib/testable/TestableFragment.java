package com.edavtyan.materialplayer.lib.testable;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.utils.advanced.GenericFragment;

public class TestableFragment extends GenericFragment {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		baseOnCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		baseOnStart();
	}

	@Override
	public void onStop() {
		baseOnStop();
	}

	@Override
	public void onDestroy() {
		baseOnDestroy();
	}

	public void baseOnCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void baseOnStart() {
		super.onStart();
	}

	public void baseOnStop() {
		super.onStop();
	}

	public void baseOnDestroy() {
		super.onDestroy();
	}
}
