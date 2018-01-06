package com.edavtyan.materialplayer.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

public abstract class BaseToolbarActivity extends BaseActivity {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActivityToolbarModule activityToolbarModule = new ActivityToolbarModule(this);
		activityToolbarModule.setTitleStringId(getToolbarTitleStringId());
		activityToolbarModule.setBackIconEnabled(isBackIconEnabled());
		addModule(activityToolbarModule);
	}

	protected boolean isBackIconEnabled() {
		return true;
	}

	protected int getToolbarTitleStringId() {
		return R.string.app_name;
	}
}
