package com.edavtyan.materialplayer.components.audioeffects;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.activities.BaseToolbarActivity;

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
