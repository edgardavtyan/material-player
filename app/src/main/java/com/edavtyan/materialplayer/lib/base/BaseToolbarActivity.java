package com.edavtyan.materialplayer.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import butterknife.BindView;

public abstract class BaseToolbarActivity extends BaseActivity {
	@BindView(R.id.toolbar) Toolbar toolbar;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActivityToolbarModule toolbarModule = new ActivityToolbarModule(this);
		toolbarModule.setTitleStringId(getToolbarTitleStringId());
		toolbarModule.setBackIconEnabled(isBackIconEnabled());
		addModule(toolbarModule);
	}

	protected boolean isBackIconEnabled() {
		return true;
	}

	protected int getToolbarTitleStringId() {
		return R.string.app_name;
	}
}
