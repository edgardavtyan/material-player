package com.edavtyan.materialplayer.views.audioeffects;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.views.lib.activities.BaseToolbarActivity;

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
