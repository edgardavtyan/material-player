package com.edavtyan.materialplayer.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.modular.activity.BaseToolbarModule;

public abstract class BaseToolbarActivity extends BaseActivity {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BaseToolbarModule baseToolbarModule = new BaseToolbarModule(this);
		baseToolbarModule.setTitleStringId(getToolbarTitleStringId());
		baseToolbarModule.setBackIconEnabled(isBackIconEnabled());
		addModule(baseToolbarModule);
	}

	protected boolean isBackIconEnabled() {
		return true;
	}

	protected int getToolbarTitleStringId() {
		return R.string.app_name;
	}
}
