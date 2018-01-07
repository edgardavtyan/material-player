package com.edavtyan.materialplayer.components.detail.album_detail;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.widget.ImageView;
import android.widget.TextView;

import com.ed.libsutils.BitmapResizer;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;
import com.edavtyan.materialplayer.utils.DpConverter;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class AlbumDetailActivityCompactTest extends ActivityTest {

	private static Navigator navigator;
	private static AlbumDetailPresenter presenter;
	private static AlbumDetailAdapter adapter;

	public static class TestAlbumDetailActivityCompact extends AlbumDetailActivityCompact {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.navigator = AlbumDetailActivityCompactTest.navigator;
			this.presenter = AlbumDetailActivityCompactTest.presenter;
			this.adapter = AlbumDetailActivityCompactTest.adapter;
			super.onCreate(savedInstanceState);
		}
	}

	@Rule
	public final ActivityTestRule<TestAlbumDetailActivityCompact> activityRule
			= new ActivityTestRule<>(TestAlbumDetailActivityCompact.class, false, false);

	private TestAlbumDetailActivityCompact activity;

	@Override
	public void beforeEach() {
		super.beforeEach();

		navigator = mock(Navigator.class);
		presenter = mock(AlbumDetailPresenter.class);
		adapter = mock(AlbumDetailAdapter.class);
		app.setAlbumDetailComponent(mock(AlbumDetailComponent.class));

		activity = activityRule.launchActivity(null);
	}

	@Test
	public void set_album_title() {
		TextView titleView = (TextView) activity.findViewById(R.id.title);
		runOnUiThread(() -> activity.setAlbumTitle("title"));
		assertThat(titleView.getText()).isEqualTo("title");
	}

	@Test
	public void set_album_info_in_portrait_mode() {
		TextView infoTopView = (TextView) activity.findViewById(R.id.info_top);
		TextView infoBottomView = (TextView) activity.findViewById(R.id.info_bottom);
		runOnUiThread(() -> activity.setAlbumInfo("artist", 1, 61123));
		assertThat(infoTopView.getText()).isEqualTo("artist");
		assertThat(infoBottomView.getText()).isEqualTo("1 Track, 1 min.");
	}

	@Test
	@Ignore
	public void set_album_info_in_landscape_mode() {
		TextView infoView = (TextView) activity.findViewById(R.id.info);
		runOnUiThread(() -> activity.setAlbumInfo("artist", 2, 122234));
		assertThat(infoView.getText()).isEqualTo("artist \u2022 2 Tracks");
	}

	@Test
	public void set_fallback_image_if_art_is_null() {
		ImageView artView = (ImageView) activity.findViewById(R.id.art);
		runOnUiThread(() -> activity.setAlbumImage(null));
		assertThat(artView).hasImageResource(R.drawable.fallback_cover);
	}

	@Test
	public void set_given_image_if_it_is_not_null() {
		ImageView artView = (ImageView) activity.findViewById(R.id.art);
		Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		runOnUiThread(() -> activity.setAlbumImage(bitmap));
		int scaledBitmapSize = DpConverter.convertDpToPixel(120);
		Bitmap scaledBitmap = BitmapResizer.resize(bitmap, scaledBitmapSize);
		assertThat(artView).hasImageBitmap(scaledBitmap);
	}

	@Test
	public void go_to_now_playing_screen_via_navigator() {
		activity.gotoNowPlaying();
		verify(navigator).gotoNowPlaying();
	}
}
