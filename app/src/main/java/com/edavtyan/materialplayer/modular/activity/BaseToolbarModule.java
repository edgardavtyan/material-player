package com.edavtyan.materialplayer.modular.activity;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.ActivityModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseToolbarModule extends ActivityModule {

	private static final int DEFAULT_TITLE_STRING_ID = R.string.app_name;
	private static final boolean DEFAULT_BACK_ICON_ENABLED = true;

	@BindView(R.id.toolbar) Toolbar toolbar;

	private final AppCompatActivity activity;

	private int titleStringId;
	private boolean isBackIconEnabled;

	public BaseToolbarModule(AppCompatActivity activity) {
		this.activity = activity;
		titleStringId = DEFAULT_TITLE_STRING_ID;
		isBackIconEnabled = DEFAULT_BACK_ICON_ENABLED;
	}

	public void setTitleStringId(@StringRes int titleStringId) {
		this.titleStringId = titleStringId;
	}

	public void setBackIconEnabled(boolean isBackIconEnabled) {
		this.isBackIconEnabled = isBackIconEnabled;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		ButterKnife.bind(this, activity);

		toolbar.setTitle(activity.getResources().getString(titleStringId));
		activity.setSupportActionBar(toolbar);
		if (isBackIconEnabled) activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			activity.finish();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
