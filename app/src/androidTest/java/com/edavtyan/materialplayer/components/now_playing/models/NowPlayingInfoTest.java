package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NowPlayingInfoTest extends NowPlayingViewTest {
	private NowPlayingMvp.View.Info info;

	@Override
	public void beforeEach() {
		super.beforeEach();
		info = new NowPlayingInfo(activity);
	}

	@Test
	public void setTitle_setTitleViewText() {
		TextView titleView = (TextView) activity.findViewById(R.id.title);
		runOnUiThread(() -> info.setTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setInfo_setInfoViewTextWithPattern() {
		TextView infoView = (TextView) activity.findViewById(R.id.info);
		runOnUiThread(() -> info.setInfo("artist", "album"));
		assertThat(infoView.getText()).isEqualTo("artist \u2022 album");
	}
}
