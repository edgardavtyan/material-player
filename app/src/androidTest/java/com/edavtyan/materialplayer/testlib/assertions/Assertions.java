package com.edavtyan.materialplayer.testlib.assertions;

import android.os.Bundle;

import com.edavtyan.materialplayer.ui.audio_effects.views.EqualizerBandView;
import com.edavtyan.materialplayer.ui.audio_effects.views.TitledSeekbar;

public final class Assertions {
	public static TitledSeekbarAssertion assertThat(TitledSeekbar seekbar) {
		return new TitledSeekbarAssertion(seekbar);
	}

	public static EqualizerBandAssert assertThat(EqualizerBandView band) {
		return new EqualizerBandAssert(band);
	}

	public static BundleAssert assertThatBundle(Bundle bundle) {
		return new BundleAssert(bundle);
	}
}
