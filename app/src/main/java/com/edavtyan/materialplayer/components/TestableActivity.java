package com.edavtyan.materialplayer.components;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.activities.BaseToolbarActivity;

@SuppressLint("MissingSuperCall")
public abstract class TestableActivity extends BaseToolbarActivity {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		baseOnCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		baseOnDestroy();
	}

	public void baseOnCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void baseOnDestroy() {
		super.onDestroy();
	}
}
