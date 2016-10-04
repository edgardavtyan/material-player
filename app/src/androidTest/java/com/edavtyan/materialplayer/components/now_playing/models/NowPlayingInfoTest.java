package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class NowPlayingInfoTest extends BaseTest {
	private NowPlayingMvp.View.Info info;
	private TextView titleView;
	private TextView infoView;

	@Override
	public void beforeEach() {
		super.beforeEach();

		titleView = new TextView(context);
		infoView = new TextView(context);

		NowPlayingActivity activity = mock(NowPlayingActivity.class);
		doReturn(context.getResources()).when(activity).getResources();
		doReturn(titleView).when(activity).findView(R.id.title);
		doReturn(infoView).when(activity).findView(R.id.info);

		info = new NowPlayingInfo(activity);
	}

	@Test
	public void setTitle_setTitleViewText() {
		info.setTitle("title");
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setInfo_setInfoViewTextWithPattern() {
		info.setInfo("artist", "album");
		assertThat(infoView.getText()).isEqualTo("artist - album");
	}
}
