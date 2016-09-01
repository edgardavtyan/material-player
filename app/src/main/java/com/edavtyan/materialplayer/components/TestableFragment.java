package com.edavtyan.materialplayer.components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class TestableFragment extends Fragment {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		baseOnCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		baseOnDestroy();
	}

	public void baseOnCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void baseOnDestroy() {
		super.onDestroy();
	}
}
