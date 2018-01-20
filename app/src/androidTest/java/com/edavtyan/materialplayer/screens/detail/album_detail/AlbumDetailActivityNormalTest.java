package com.edavtyan.materialplayer.screens.detail.album_detail;

import android.annotation.SuppressLint;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class AlbumDetailActivityNormalTest extends BaseAlbumDetailActivityTest {

	public static class TestAlbumDetailActivityNormal extends AlbumDetailActivityNormal {
		@Override
		protected AlbumDetailComponent getComponent() {
			return createMockComponent(this);
		}
	}

	@Rule
	public final ActivityTestRule<TestAlbumDetailActivityNormal> activityRule
			= new ActivityTestRule<>(TestAlbumDetailActivityNormal.class, false, false);

	private TestAlbumDetailActivityNormal activity;

	@Override
	public void beforeEach() {
		super.beforeEach();
		activity = activityRule.launchActivity(null);
	}

	@Test
	public void setAlbumTitle_setTitleViewText() {
		TextView titleView = (TextView) activity.findViewById(R.id.title);
		runOnUiThread(() -> activity.setAlbumTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void setAlbumInfo_setInfoTextWithPattern() {
		TextView infoView = (TextView) activity.findViewById(R.id.info);
		runOnUiThread(() -> activity.setAlbumInfo("artist", 9, 0));
		assertThat(infoView.getText()).isEqualTo("artist \u2022 9 Tracks");
	}

	@Test
	public void gotoNowPlaying_callNavigator() {
		activity.gotoNowPlaying();
		verify(navigator).gotoNowPlaying();
	}
}
