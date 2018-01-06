package com.edavtyan.materialplayer.components.detail.album_detail;

import android.annotation.SuppressLint;
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
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class AlbumDetailActivityTest extends ActivityTest {
	@Rule
	public final ActivityTestRule<AlbumDetailActivity> activityRule
			= new ActivityTestRule<>(AlbumDetailActivity.class, false, false);

	private AlbumDetailActivity activity;
	private Navigator navigator;

	@Override
	public void beforeEach() {
		super.beforeEach();

		navigator = mock(Navigator.class);
		AlbumDetailPresenter presenter = mock(AlbumDetailPresenter.class);
		AlbumDetailAdapter adapter = mock(AlbumDetailAdapter.class);

		AlbumDetailFactory factory = mock(AlbumDetailFactory.class);
		when(factory.getPresenter()).thenReturn(presenter);
		when(factory.getAdapter()).thenReturn(adapter);
		when(factory.getNavigator()).thenReturn(navigator);
		app.setAlbumDetailFactory(factory);

		activity = startActivity(AlbumDetailActivity.class);
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
