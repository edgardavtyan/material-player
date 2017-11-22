package com.edavtyan.materialplayer.lib.testable;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class TestableFragment extends Fragment {
	@Override
	@SuppressLint("MissingSuperCall")
	public void onCreate(@Nullable Bundle savedInstanceState) {
		baseOnCreate(savedInstanceState);
	}

	@Override
	@SuppressLint("MissingSuperCall")
	public void onStart() {
		baseOnStart();
	}

	@Override
	@SuppressLint("MissingSuperCall")
	public void onStop() {
		baseOnStop();
	}

	@Override
	@SuppressLint("MissingSuperCall")
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
