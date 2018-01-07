package com.edavtyan.materialplayer.components.detail.album_detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class AlbumDetailActivityNormalTest extends ActivityTest {

	private static Navigator navigator;
	private static AlbumDetailPresenter presenter;
	private static AlbumDetailAdapter adapter;

	public static class TestAlbumDetailActivityNormal extends AlbumDetailActivityNormal {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.navigator = AlbumDetailActivityNormalTest.navigator;
			this.presenter = AlbumDetailActivityNormalTest.presenter;
			this.adapter = AlbumDetailActivityNormalTest.adapter;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected AlbumDetailComponent getComponent() {
			return mock(AlbumDetailComponent.class);
		}
	}

	@Rule
	public final ActivityTestRule<TestAlbumDetailActivityNormal> activityRule
			= new ActivityTestRule<>(TestAlbumDetailActivityNormal.class, false, false);

	private TestAlbumDetailActivityNormal activity;

	@Override
	public void beforeEach() {
		super.beforeEach();
		navigator = mock(Navigator.class);
		presenter = mock(AlbumDetailPresenter.class);
		adapter = mock(AlbumDetailAdapter.class);
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
