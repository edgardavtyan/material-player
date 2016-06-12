package com.edavtyan.materialplayer.app.views.audioeffects;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.views.lib.activities.BaseToolbarActivity;

public class AudioEffectsActivity extends BaseToolbarActivity {
	/* BaseActivity */

	@Override
	public int getLayoutId() {
		return R.layout.activity_effects;
	}

	/* BaseToolbarActivity */

	@Override
	public int getToolbarTitleStringId() {
		return R.string.audio_effects_title;
	}
}
