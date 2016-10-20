package com.edavtyan.materialplayer.lib.testable;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

@SuppressLint("MissingSuperCall")
public abstract class TestableActivity extends AppCompatActivity {
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
