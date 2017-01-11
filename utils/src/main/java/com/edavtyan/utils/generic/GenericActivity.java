package com.edavtyan.utils.generic;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

public abstract class GenericActivity extends AppCompatActivity {
	@SuppressWarnings("unchecked")
	public <T> T findView(@IdRes int id) {
		return (T) findViewById(id);
	}
}
