package com.edavtyan.utils.advanced;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

public class GenericActivity extends AppCompatActivity {
	@SuppressWarnings("unchecked")
	public <T> T findView(@IdRes int id) {
		return (T) findViewById(id);
	}
}
